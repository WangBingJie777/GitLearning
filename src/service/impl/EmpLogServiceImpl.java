package service.impl;

import bean.EmpLog;
import dao.EmpLogDao;
import dao.impl.EmpLogDaoImpl;
import service.EmpLogService;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-31 11:50
 */
public class EmpLogServiceImpl implements EmpLogService {
    private EmpLogDao empLogDao = new EmpLogDaoImpl();
    @Override
    public List<EmpLog> showEmpLogs() {
        return empLogDao.queryAllLogs();
    }
}
