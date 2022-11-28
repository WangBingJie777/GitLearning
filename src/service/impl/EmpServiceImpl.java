package service.impl;

import bean.Drug;
import bean.Emp;
import bean.Page;
import dao.EmpDao;
import dao.impl.EmpDaoImpl;
import service.EmpService;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-27 10:14
 */
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public int addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public int delEmp(int empId) {
        return empDao.deleteEmp(empId);
    }

    @Override
    public int updateEmp(Emp emp) {
        return empDao.updateEmp(emp);
    }

    @Override
    public Emp findEmpById(int empId) {
        return empDao.findById(empId);
    }

    @Override
    public Page<Emp> page(int pageNo, int pageSize) {
        Page<Emp> page = new Page<Emp>();
        page.setPageNo(pageNo);//页码
        page.setPageSize(pageSize);//一页的大小
        int pageTotalCount =empDao.queryForPageTotalCount();//总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);//设置总页码

        //求当前页数据
        int begin = (page.getPageNo()-1) * pageSize;//求当前页数据的开始索引
        List<Emp> items = empDao.queryForPageItems(begin,pageSize);
        page.setItems(items);//设置当前页数据
        return page;
    }

    @Override
    public Emp empLogin(String phone, String password) {
        return empDao.findByPhoneAndPwd(phone,password);
    }
}
