package dao;

import bean.OrderItem;

import java.util.List;

/**
 * @author wbj
 * @date 2022/7/29 - 9:54
 */
public interface OrderItemDao {
    // 保存订单项
    public int saveOrderItem(OrderItem orderItem);

    //查询某个订单项的明细
    List<OrderItem> queryOrderItemDetail(String orderId);
}
