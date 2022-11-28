package dao.impl;

import bean.Drug;
import dao.DrugDao;

import java.awt.print.Book;
import java.util.List;

/**
 * @author wbj
 * @create 2022-08-25 8:48
 */
public class DrugDaoImpl extends BaseDao implements DrugDao {
    @Override
    public int addDrug(Drug drug) {
        String sql = "INSERT INTO drug(drug_name,producer,price,create_date,`EXP`,`usage`,`count`) values(?,?,?,?,?,?,?)";
        return update(sql, drug.getDrug_name(), drug.getProducer(),drug.getPrice(), drug.getCreate_date(), drug.getExp(), drug.getUsage(), drug.getCount());
    }

    @Override
    public int deleteDrugById(Integer id) {
        String sql = "DELETE FROM drug WHERE drug_id = ?";
        return update(sql, id);
    }

    @Override
    public int updateDrug(Drug drug) {
        String sql = "UPDATE drug SET drug_name=?,producer=?,price=?,create_date=?,`EXP`=?,`usage`=?,`count`=? where drug_id =?";
        return update(sql, drug.getDrug_name(), drug.getProducer(),drug.getPrice(), drug.getCreate_date(), drug.getExp(), drug.getUsage(), drug.getCount(),drug.getDrug_id());
    }

    @Override
    public Drug queryDrugById(Integer id) {
        String sql = "SELECT * FROM drug WHERE drug_id=?";
        return queryForOne(Drug.class,sql,id);
    }


    @Override
    public List<Drug> queryDrugs() {
        String sql = "SELECT * FROM drug WHERE drug_name LIKE ?";
        return queryForList(Drug.class, sql);//不给args传，默认为null。因为咱无？故不用传args
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT count(*) FROM drug";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Drug> queryForPageItems(int begin, int pageSize) {
        String sql = "SELECT * FROM drug LIMIT ?,? ";
        return queryForList(Drug.class, sql, begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "SELECT COUNT(*) FROM drug WHERE price BETWEEN ? AND ? ";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Drug> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "SELECT * FROM drug WHERE price BETWEEN ? AND ? ORDER BY price LIMIT ?,?";
        return queryForList(Drug.class, sql,min,max ,begin,pageSize);//细心！注意参数的对应
    }

    @Override
    public Integer queryForPageTotalCountByName(String drugName) {
        String sql = "SELECT COUNT(*) FROM drug WHERE drug_name LIKE ?";
        Number count = (Number) queryForSingleValue(sql,'%'+drugName+'%');
        return count.intValue();
    }

    @Override
    public List<Drug> queryForPageItemsByName(int begin, int pageSize, String drugName) {
        String sql = "SELECT * FROM drug WHERE drug_name LIKE ? ORDER BY price LIMIT ?,?";
        return queryForList(Drug.class,sql,'%'+drugName+'%',begin,pageSize);
    }

}
