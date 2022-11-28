package service;

import bean.Cart;
import bean.Order;
import bean.OrderItem;

import java.util.List;

/**
 * @author wbj
 * @date 2022/7/29 - 14:49
 */
public interface OrderService {
    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, int userId);

    /**
     * 查询我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrders(int userId);

    /**
     * 查看所有订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId
     * @return
     */
    public int sendOrder(String orderId);

    /**
     * 收货
     * @param orderId
     * @return
     */

    public int receiveOrder(String  orderId);

    public List<OrderItem> showOrderDetail(String orderId);

}
