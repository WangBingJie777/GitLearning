package dao.impl;

import bean.DrugLog;
import dao.DrugLogDao;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-30 23:14
 */
public class DrugLogDaoImpl extends BaseDao implements DrugLogDao {
    @Override
    public List<DrugLog> queryAlllogs() {
        String sql = "SELECT * FROM drug_log ORDER BY log_id DESC ";
        return queryForList(DrugLog.class,sql);
    }
}
