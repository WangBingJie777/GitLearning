package dao;

import bean.Order;

import java.util.List;

/**
 * @author wbj
 * @date 2022/7/29 - 9:49
 */
public interface OrderDao {
    //保存订单到数据
    public int saveOrder(Order order);

    //查看我的订单
    public List<Order> queryMyOrders(Integer userId);

    //查看所有订单
    public List<Order> queryAllOrders();

    // 修改订单的状态
    public int updateOrderStatus(String orderId,Integer status);



}
