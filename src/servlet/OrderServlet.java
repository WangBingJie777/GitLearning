package servlet;

import bean.Cart;
import bean.Order;
import bean.OrderItem;
import bean.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wbj
 * @date 2022/7/29 - 18:31
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单。点击【去结账】就会执行这个方法。
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //1.先获取获取Cart购物车对象和userId.
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");//因为咱之前登录后，就把登录的用户对象放到Session里了，所以，这可以用。不用传参了。
        //如果没有登录，则loginUser就是空的，需要跳到登录页面
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;//必须return，后面的程序不用执行了。
        }
        int userId = loginUser.getUser_id();
    // 2.调用OrderService.createOrder(Cart,UserId)生成订单.得到订单号
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        //3.把生成的订单的订单号保存到session域中
        req.getSession().setAttribute("orderId", orderId);
        //4.重定向到结账页面checkout.jsp 。用重定向是为了防止重复提交.
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    public void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.showMyOrders(loginUser.getUser_id());
        req.setAttribute("myOrders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);


    }

    public void showMyOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求参数：orderId
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItemsDetail = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItemsDetail",orderItemsDetail);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    public void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> allOrders = orderService.showAllOrders();
        req.setAttribute("allOrders",allOrders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    public void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
    public void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
