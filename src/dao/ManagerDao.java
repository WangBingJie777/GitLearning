package dao;

import bean.Manager;

/**
 * @author wbj
 * @create 2022-09-02 13:05
 */
public interface ManagerDao {
    Manager findByLogNameAndPassword(String logName,String password);
}
