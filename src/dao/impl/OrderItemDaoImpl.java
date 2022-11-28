package dao.impl;

import bean.OrderItem;
import dao.OrderItemDao;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-27 19:37
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_item(order_id,item_name,item_price,item_count,total_price) VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getOrder_id(),orderItem.getItem_name(),orderItem.getItem_price(),orderItem.getItem_count(),orderItem.getTotal_price());
    }

    @Override
    public List<OrderItem> queryOrderItemDetail(String orderId ) {
        String sql = "SELECT * FROM order_item WHERE  order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
