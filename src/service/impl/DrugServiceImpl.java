package service.impl;

import bean.Drug;
import bean.Page;
import dao.DrugDao;
import dao.impl.DrugDaoImpl;
import service.DrugService;


import java.util.List;

/**
 * @author wbj
 * @create 2022-08-25 9:54
 */
public class DrugServiceImpl implements DrugService {
    private DrugDao drugDao = new DrugDaoImpl();
    @Override
    public int addDrug(Drug drug) {
        return drugDao.addDrug(drug);
    }

    @Override
    public int delDrug(int drugId) {
        return drugDao.deleteDrugById(drugId);
    }

    @Override
    public int updateDrug(Drug drug) {
        return drugDao.updateDrug(drug);
    }

    @Override
    public Drug findDrugById(int drugId) {
        return drugDao.queryDrugById(drugId);
    }

    @Override
    public List<Drug> queryDrugs() {
        return drugDao.queryDrugs();
    }


    @Override
    public Page<Drug> page(int pageNo, int pageSize) {
        Page<Drug> page = new Page<Drug>();
        page.setPageNo(pageNo);//页码
        page.setPageSize(pageSize);//一页的大小
        int pageTotalCount =drugDao.queryForPageTotalCount();//总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);//设置总页码

        //求当前页数据
        int begin = (page.getPageNo()-1) * pageSize;//求当前页数据的开始索引
        List<Drug> items = drugDao.queryForPageItems(begin,pageSize);
        page.setItems(items);//设置当前页数据
        return page;
    }

    @Override
    public Page<Drug> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Drug> page = new Page();
        page.setPageNo(pageNo);//页码
        page.setPageSize(pageSize);//一页的大小
        int pageTotalCount = drugDao.queryForPageTotalCountByPrice(min,max);//总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);//设置总页码

        //求当前页数据
        int begin = (page.getPageNo()-1) * pageSize;//求当前页数据的开始索引
        List<Drug> items = drugDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);//设置当前页数据
        return page;
    }

    @Override
    public Page<Drug> pageByName(int pageNo, int pageSize, String drugName) {
        Page<Drug> page = new Page();
        page.setPageNo(pageNo);//页码
        page.setPageSize(pageSize);//一页的大小
        int pageTotalCount = drugDao.queryForPageTotalCountByName(drugName);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);//设置总页码

        //求当前页数据
        int begin = (page.getPageNo()-1) * pageSize;//求当前页数据的开始索引
        List<Drug> items = drugDao.queryForPageItemsByName(begin,pageSize,drugName);
        page.setItems(items);//设置当前页数据
        return page;
    }

}
