package bean;

import java.math.BigDecimal;

/**
 * @author wbj
 * @create 2022-08-27 17:04
 */
public class OrderItem {
    private Integer id;//不是药品编号。仅仅是数据库中一个自增+1的主键。用户的订单里不用显示药品的编号
    private String item_name;//药品名
    private BigDecimal item_price;//单价
    private Integer item_count;//订单中某种药品的数量
    private BigDecimal total_price;//单价*数量=某种药品的总价
    private String order_id;//这一条记录在哪个订单里

    public OrderItem() {
    }

    public OrderItem(Integer id, String item_name, BigDecimal item_price, Integer item_count, BigDecimal total_price, String order_id) {
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_count = item_count;
        this.total_price = total_price;
        this.order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public BigDecimal getItem_price() {
        return item_price;
    }

    public void setItem_price(BigDecimal item_price) {
        this.item_price = item_price;
    }

    public Integer getItem_count() {
        return item_count;
    }

    public void setItem_count(Integer item_count) {
        this.item_count = item_count;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                ", item_count=" + item_count +
                ", total_price=" + total_price +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}
