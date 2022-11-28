package bean;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author wbj
 * @create 2022-08-27 9:59
 */
public class Emp {
    private Integer emp_id;
    private String emp_name;
    private String emp_gender;
    private Integer emp_age;
    private String emp_edu;
    private String emp_work;
    private String emp_phone;
    private String emp_password;

    public Emp() {
    }

    public Emp(Integer emp_id, String emp_name, String emp_gender, Integer emp_age, String emp_edu, String emp_work, String emp_phone, String emp_password) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_gender = emp_gender;
        this.emp_age = emp_age;
        this.emp_edu = emp_edu;
        this.emp_work = emp_work;
        this.emp_phone = emp_phone;
        this.emp_password = emp_password;
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

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public Integer getEmp_age() {
        return emp_age;
    }

    public void setEmp_age(Integer emp_age) {
        this.emp_age = emp_age;
    }

    public String getEmp_edu() {
        return emp_edu;
    }

    public void setEmp_edu(String emp_edu) {
        this.emp_edu = emp_edu;
    }

    public String getEmp_work() {
        return emp_work;
    }


    public void setEmp_work(String emp_work) {
        this.emp_work = emp_work;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", emp_gender='" + emp_gender + '\'' +
                ", emp_age=" + emp_age +
                ", emp_edu='" + emp_edu + '\'' +
                ", emp_work='" + emp_work + '\'' +
                ", emp_phone='" + emp_phone + '\'' +
                ", emp_password='" + emp_password + '\'' +
                '}';
    }
}
