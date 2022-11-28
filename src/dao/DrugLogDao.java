package dao;

import bean.DrugLog;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-30 23:13
 */
public interface DrugLogDao {
    List<DrugLog> queryAlllogs();
}
