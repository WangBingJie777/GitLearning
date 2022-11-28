package service.impl;

import bean.Manager;
import dao.ManagerDao;
import dao.impl.ManagerDaoImpl;
import service.ManagerService;

/**
 * @author wbj
 * @create 2022-09-02 13:12
 */
public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public Manager managerLogin(String logName, String password) {
        return managerDao.findByLogNameAndPassword(logName,password);
    }
}
