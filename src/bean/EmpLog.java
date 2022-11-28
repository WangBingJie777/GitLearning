package bean;

/**对应员工日志表
 * @author wbj
 * @create 2022-08-31 10:23
 */
public class EmpLog {
    private Integer log_id;
    private String log_time;
    private Integer emp_id;
    private String emp_name;
    private String type;

    public EmpLog() {
    }

    public EmpLog(Integer log_id, String log_time, Integer emp_id, String emp_name, String type) {
        this.log_id = log_id;
        this.log_time = log_time;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.type = type;
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

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EmpLog{" +
                "log_id=" + log_id +
                ", log_time='" + log_time + '\'' +
                ", emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
