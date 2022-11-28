package dao;

import bean.EmpLog;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-31 11:47
 */
public interface EmpLogDao {
    List<EmpLog> queryAllLogs();
}
