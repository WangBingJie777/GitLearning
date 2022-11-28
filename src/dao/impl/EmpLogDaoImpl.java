package dao.impl;

import bean.EmpLog;
import dao.EmpLogDao;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-31 11:48
 */
public class EmpLogDaoImpl extends BaseDao implements EmpLogDao {
    @Override
    public List<EmpLog> queryAllLogs() {
        String sql = "SELECT * FROM emp_log ORDER BY log_id DESC ";
        return queryForList(EmpLog.class,sql);

    }
}
