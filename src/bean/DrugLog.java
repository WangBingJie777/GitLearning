package bean;

import java.math.BigDecimal;

/**对应药品日志表
 * 药品日志。记录对药品的增、删、改操作。
 * 如果是增，就记录新增的药品信息。
 * 如果是删、改，就记录 删、 改 前的药品信息
 * @author wbj
 * @create 2022-08-30 22:27
 */
public class DrugLog {
    private Integer log_id;//自增
    private String log_time;//字符串或TimeStamp都可以
    private Integer drug_id;
    private String drug_name;
    private BigDecimal price;
    private Integer count;
    private String type;//操作类型：删、改、增
    private Integer emp_id;//哪个员工执行了此操作

    public DrugLog() {
    }


    public DrugLog(Integer log_id, String log_time, Integer drug_id, String drug_name, BigDecimal price, Integer count, String type, Integer emp_id) {
        this.log_id = log_id;
        this.log_time = log_time;
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.price = price;
        this.count = count;
        this.type = type;
        this.emp_id = emp_id;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public String toString() {
        return "drug_log{" +
                "log_id=" + log_id +
                ", log_time='" + log_time + '\'' +
                ", drug_id=" + drug_id +
                ", drug_name='" + drug_name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", type='" + type + '\'' +
                ", emp_id=" + emp_id +
                '}';
    }
}
