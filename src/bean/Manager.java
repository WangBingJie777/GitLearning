package bean;

/**
 * @author wbj
 * @create 2022-09-02 12:55
 */
public class Manager {
    private Integer id;
    private String log_name;
    private String log_password;

    public Manager() {
    }

    public Manager(Integer id, String log_name, String log_password) {
        this.id = id;
        this.log_name = log_name;
        this.log_password = log_password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog_name() {
        return log_name;
    }

    public void setLog_name(String log_name) {
        this.log_name = log_name;
    }

    public String getLog_password() {
        return log_password;
    }

    public void setLog_password(String log_password) {
        this.log_password = log_password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", log_name='" + log_name + '\'' +
                ", log_password='" + log_password + '\'' +
                '}';
    }
}
