package dao;

import bean.Drug;
import bean.Emp;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-27 10:08
 */
public interface EmpDao {
    Emp findByPhoneAndPwd(String phone,String password);
    int addEmp(Emp emp);
    Emp findByName(String name);
    Emp findById(int empId);
    int deleteEmp(int empId);
    int updateEmp(Emp emp);
    List<Emp> queryEmps();
    List<Emp> queryForPageItems(int begin, int pageSize);//用来查询当前页所要展示的Drug数据
    Integer queryForPageTotalCount();


}
