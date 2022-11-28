package dao.impl;

import bean.Order;
import dao.OrderDao;

import java.util.List;

/**
 * @author wbj
 * @create 2022-08-27 17:40
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `ORDER`(order_id,userId,order_time,order_price,`status`) VALUES(?,?,?,?,?)";
        Object[] args = {
                order.getOrder_id(),
                order.getUserId(),
                order.getOrder_time(),
                order.getOrder_price(),
                order.getStatus()
        };
        return update(sql,args);
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "SELECT * FROM `order` WHERE userId=? ORDER BY order_time DESC";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "SELECT * FROM `order` ";
        return queryForList(Order.class,sql);
    }

    @Override
    public int updateOrderStatus(String orderId, Integer status) {
        String sql = "UPDATE `order` SET `status`=? WHERE order_id=?";
        return update(sql,status,orderId);
    }
}
