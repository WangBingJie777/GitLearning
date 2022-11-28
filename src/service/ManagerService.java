package service;

import bean.Manager;

/**
 * @author wbj
 * @create 2022-09-02 13:08
 */
public interface ManagerService {
    Manager managerLogin(String logName,String password);
}
