package servlet;

import bean.Cart;
import bean.CartItem;
import bean.Drug;
import com.google.gson.Gson;
import service.DrugService;
import service.impl.DrugServiceImpl;
import utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wbj
 * @date 2022/8/28 - 16:07
 */
public class CartServlet extends BaseServlet{
    DrugService drugService = new DrugServiceImpl();

    //加入购物车.此方法不再使用。升级为下面的ajaxAddItem()方法
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数：商品编号. 参数名一定要和你请求转发链接里的一样，否则得不到，就会报webUtils错误，id值也永远是0.
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        // 2. 调用Service里的方法，得到药品对象
        Drug drug = drugService.findDrugById(id);
        // 3. 把药品信息转换成CartItem商品项
        CartItem cartItem = new CartItem(drug.getDrug_id(),drug.getDrug_name(),1,drug.getPrice(),drug.getPrice());
        // 4. 调用Cart.addItem(CartItem)添加商品到购物车
        //Cart cart = new Cart();不能直接new，这是错的，因为这样会导致点一次“加入购物车”，就会new一个Cart，
        // 购物车从Session中获取，具体如下：
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            //如果Session中没有Cart，就创建一个，放到Session里.下次请求时，就可以从Session里拿到这个Cart里
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 最后一个添加到购物车的商品名称，保存到session域中，用于在首页输出提示信息。
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向redirect回 商品列表。从哪里来，跳回哪里去。
        // 举例：http://localhost:8080/drugsys3/client/drugServlet?action=page&pageNo=2比如从这个地址对应的
        // 页面中，点击了“加入购物车”，点完后，我们要跳回这个地址对应的页面

        //5. 重定向回 原来商品所在的地址页面. 实际上效果就是，你点击“添加到购物车”后，给你的感觉是页面没发生变化，但底层实际上
        //是又重新跳回来的，并不是真的没有变化。给人的感觉就是“刷新”，数据会重新从数据库加载过来，这就实现了页面的动态变化。
        resp.sendRedirect(req.getHeader("Referer"));


    }

    /**
     * 删除购物车中的商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取由a标签 发送过来的 要删商品的id。（请求参数）
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        // 2.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //调用Cart.deleteItem(id)删除商品项
            cart.deleteItem(id);
        //5. 重定向回 原来商品所在的地址页面. 从哪来回哪去。用户体验效果：点击“删除”后，
        // 那一项立刻在屏幕上消失了，用户会感觉刷新了一下。其实底层是重定向回那个购物车展示页面。
        //    所以，所有的数据也会更新，因为底层是重定向，相当于把数据重新写了一遍，一个新页面替换掉原来的页面。
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            // 调用Cart.clear()清空购物车
            cart.clear();
            //重定向会购物车页面。从哪里回哪去
            resp.sendRedirect(req.getHeader("Referer"));

        }
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数:商品编号、商品数量
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        int count = webUtils.parseInt(req.getParameter("count"), 0);
        //2.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            // 调用Cart.updateCount()修改商品的数量
            cart.updateCount(id, count);
            // 重定向回原页面。（其实就是实现了刷新）
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
    //前面的代码和addItem()里的一模一样，只有最后一步不同，最后一步不再用重定向，而是使用ajax。
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数：商品编号. 参数名一定要和你请求转发链接里的一样，否则得不到，就会报webUtils错误，id值也永远是0.
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        // 2. 调用Service层 得到Drug对象
        Drug drug = drugService.findDrugById(id);
        // 3. 把药品信息转换成CartItem商品项
        CartItem cartItem = new CartItem(drug.getDrug_id(),drug.getDrug_name(),1,drug.getPrice(),drug.getPrice());
        // 4. 调用Cart.addItem(CartItem)添加商品到购物车。不能直接new，要先从Session中取，若没有才new，而且new完后要放到Session中
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            //如果Session中没有Cart，就创建一个，放到Session里.下次请求时，就可以从Session里拿到这个Cart里
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 5 最后一个添加到购物车的商品名称，保存到session域中，用于在首页输出提示信息。
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        //用响应流返回，给前端页面使用
        resp.getWriter().write(resultMapJsonString);
    }
}
