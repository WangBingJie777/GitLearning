package service.impl;

import bean.DrugLog;
import dao.DrugLogDao;
import dao.impl.DrugLogDaoImpl;
import service.DrugLogService;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-31 8:53
 */
public class DrugLogServiceImpl implements DrugLogService {
    private DrugLogDao drugLogDao = new DrugLogDaoImpl();
    @Override
    public List<DrugLog> showDrugLogs() {
        return drugLogDao.queryAlllogs();
    }
}
