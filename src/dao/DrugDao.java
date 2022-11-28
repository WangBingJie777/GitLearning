package dao;


import bean.Drug;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-25 8:47
 */
public interface DrugDao {
    public int addDrug(Drug drug);

    public int deleteDrugById(Integer id);

    public int updateDrug(Drug drug);

    public Drug queryDrugById(Integer id);


    public List<Drug> queryDrugs();

    Integer queryForPageTotalCount();

    List<Drug> queryForPageItems(int begin, int pageSize);//用来查询当前页所要展示的Drug数据

    Integer queryForPageTotalCountByPrice(int min, int max);
    Integer queryForPageTotalCountByName(String drugName);

    List<Drug> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
    List<Drug> queryForPageItemsByName(int begin, int pageSize, String drugName);

}
