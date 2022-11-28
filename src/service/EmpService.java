package service;

import bean.Emp;
import bean.Page;

/**
 * @author wbj
 * @create 2022-08-27 10:14
 */
public interface EmpService {
    Emp empLogin(String phone,String password);
    int addEmp(Emp emp);
    int delEmp(int empId);
    int updateEmp(Emp emp);
    Emp findEmpById(int empId);
    Page<Emp> page(int pageNo,int pageSize);
}
