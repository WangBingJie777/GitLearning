package service;

import bean.Drug;
import bean.Page;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-25 9:54
 */
public interface DrugService {
    int addDrug(Drug drug);
    int delDrug(int drugId);
    int updateDrug(Drug drug);
    Drug findDrugById(int drugId);
    // public List<Drug> findDrugByName(String drugName);
    List<Drug> queryDrugs();
    public Page<Drug> page(int pageNo, int pageSize);
    public Page<Drug> pageByPrice(int pageNo, int pageSize, int min, int max);
    public Page<Drug> pageByName(int pageNo, int pageSize,String drugName);
}
