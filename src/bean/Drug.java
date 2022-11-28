package bean;



import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

/**
 * @author wbj
 * @create 2022-08-22 20:14
 */

public class Drug {
    private Integer drug_id;
    private String drug_name;
    private String producer;
    private BigDecimal price;
    private String create_date;//生产日期。用字符串或TimeStamp也行
    private String exp;//保质期
    private String usage;//用途
    private Integer count;//库存
    private Integer date_diff;//`EXP`-CURDATE()  保质期日期 减去 现在日期所得，在数据库中用触发器实现该列的值的计算

    public Drug() {
    }

    public Drug(Integer drug_id, String drug_name, String producer, BigDecimal price, String create_date, String exp, String usage, Integer count,Integer date_diff) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.producer = producer;
        this.price = price;
        this.create_date = create_date;
        this.exp = exp;
        this.usage = usage;
        this.count = count;
        this.date_diff = date_diff;
    }

    public Integer getDate_diff() {
        return date_diff;
    }

    public void setDate_diff(Integer date_diff) {
        this.date_diff = date_diff;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }


    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drug_id=" + drug_id +
                ", drug_name='" + drug_name + '\'' +
                ", producer='" + producer + '\'' +
                ", price=" + price +
                ", create_date='" + create_date + '\'' +
                ", exp='" + exp + '\'' +
                ", usage='" + usage + '\'' +
                ", count=" + count +
                ", date_diff=" + date_diff +
                '}';
    }
}
