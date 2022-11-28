package dao.impl;

import bean.Manager;
import dao.ManagerDao;

/**
 * @author wbj
 * @create 2022-09-02 13:07
 */
public class ManagerDaoImpl extends BaseDao implements ManagerDao {
    @Override
    public Manager findByLogNameAndPassword(String logName, String password) {
        String sql = "SELECT * FROM manager WHERE log_name = ? AND log_password = ?";
        return queryForOne(Manager.class,sql,logName,password);
    }
}
