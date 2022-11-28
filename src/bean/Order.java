package bean;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * @author wbj
 * @create 2022-08-27 16:57
 */
public class Order {
    private String order_id;
    private Integer userId; //谁买的
    private Timestamp order_time;
    private BigDecimal order_price;//一个订单的总价格
    private Integer status;//0未发货，1已发货，2以签收

    public Order() {
    }

    public Order(String order_id, Integer userId, Timestamp order_time, BigDecimal order_price, Integer status) {
        this.order_id = order_id;
        this.userId = userId;
        this.order_time = order_time;
        this.order_price = order_price;
        this.status = status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", userId=" + userId +
                ", order_time=" + order_time +
                ", order_price=" + order_price +
                ", status=" + status +
                '}';
    }
}
