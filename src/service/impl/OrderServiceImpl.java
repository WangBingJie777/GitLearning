package service.impl;

import bean.*;
import dao.DrugDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.DrugDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import service.OrderService;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wbj
 * @create 2022-08-27 19:27
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private DrugDao drugDao = new DrugDaoImpl();
    @Override
    public String createOrder(Cart cart, int userId) {
        //订单号--唯一性:时间戳+userId+""
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个订单对象,然后保存订单到数据库.必须先创建订单，再创基订单项，才能把订单项放订单里面。
        Order order = new Order(orderId,userId,new Timestamp(new Date().getTime()),cart.getTotalPrice(),0);
        orderDao.saveOrder(order);
        //订单项其实就是购物车里的商品项
        //遍历购物车中每一个商品项转换成为订单项 ，保存到数据库
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();//value就是CartItem对象
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getPrice(),cartItem.getCount(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新库存。每加入购物车，商品的库存都要变化
            Drug drug = drugDao.queryDrugById(cartItem.getId());
            drug.setCount(drug.getCount() - cartItem.getCount());
            //保存更新后的Book对象到数据库
            drugDao.updateDrug(drug);
            //。这样，页面的数据从数据库中读的时候，由于在各地方都用了重定向，
            // 会重新从数据库中读数据，放到页面上，而且页面输出也是动态的（不是写死的），所以页面会跟着刷新，这就实现了页面的动态变化。
        }
        //结账后，清空购物车
        cart.clear();
        return orderId;//返回订单号。供外面用
    }
    @Override
    public List<Order> showMyOrders(int userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryAllOrders();
    }
//发货。修改 status，0未发货，1已发货，2已签收
    @Override
    public int sendOrder(String orderId) {
        return orderDao.updateOrderStatus(orderId,1);
    }
//收货(签收)
    @Override
    public int receiveOrder(String orderId) {
        return orderDao.updateOrderStatus(orderId,2);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemDetail(orderId);
    }

}
