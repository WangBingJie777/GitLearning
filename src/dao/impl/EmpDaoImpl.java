package dao.impl;

import bean.Drug;
import bean.Emp;
import dao.EmpDao;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-27 10:09
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {

    @Override
    public int addEmp(Emp emp) {
        String sql = "INSERT INTO employee(`emp_name`,`emp_gender`,`emp_age`,`emp_edu`,`emp_work`,`emp_phone`,`emp_password`) VALUES(?,?,?,?,?,?,?)";
        return update(sql,emp.getEmp_name(),emp.getEmp_gender(),emp.getEmp_age(),emp.getEmp_edu(),emp.getEmp_work(),emp.getEmp_phone(),emp.getEmp_password());
    }

    @Override
    public Emp findByName(String name) {
        String sql = "select * from employee where emp_name like ?";
        return queryForOne(Emp.class,sql,'%'+name+'%');
    }

    @Override
    public Emp findById(int empId) {
        String sql = "select * from employee where emp_id = ?";
        return queryForOne(Emp.class,sql,empId);
    }

    @Override
    public int deleteEmp(int empId) {
        String sql = "delete from employee where emp_id = ? ";
        return update(sql,empId);

    }

    @Override
    public int updateEmp(Emp emp) {
        String sql = "UPDATE employee SET `emp_name`=?,`emp_gender`=?,`emp_age`=?,`emp_edu`=?,`emp_work`=?,`emp_phone`=?,`emp_password`=? WHERE emp_id = ?";
        return update(sql,emp.getEmp_name(),emp.getEmp_gender(),emp.getEmp_age(),emp.getEmp_edu(),emp.getEmp_work(),emp.getEmp_phone(),emp.getEmp_password(),emp.getEmp_id());
    }

    @Override
    public List<Emp> queryEmps() {
        String sql = "select * from employee";
        return queryForList(Emp.class,sql);
    }

    @Override
    public List<Emp> queryForPageItems(int begin, int pageSize) {
        String sql = "SELECT * FROM employee LIMIT ?,? ";
        return queryForList(Emp.class, sql, begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT count(*) FROM employee";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Emp findByPhoneAndPwd(String phone,String password) {
        String sql = "SELECT * FROM employee WHERE emp_phone= ? AND emp_password=?";
        return queryForOne(Emp.class,sql,phone,password);
    }
}
