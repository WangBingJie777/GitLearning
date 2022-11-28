package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author wbj
 * @date 2022/7/22 - 16:07
 */

public abstract class BaseServlet extends HttpServlet {
    //因为有些请求是get请求，比如点击超链接。所以必须加一个doGet方法。doGet方法里面调用doPost方法，所以，其实二者做的事情是一样的。
    //这样做是为了能够同时满足get、post这两种请求。
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,RuntimeException {
        //解决post请求中文乱码问题。
        request.setCharacterEncoding("UTF-8");
        //解决响应的中文乱码问题
        response.setContentType("text/html;charset=UTF-8");

        //获取表单中的hidden标签的action属性的值，从而就知道是什么业务了，是登录还是注册，还是xxx
        String action = request.getParameter("action");
        //this指当前对象，谁调用这个方法，this就是谁
        try {
            //反射
            // 通过action这个业务鉴别字符串，明确什么业务，从而获取方法，然
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //然后调用方法
            method.invoke(this, request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //这里可以不捕获。但是必须将异常向上抛，抛给TransactionFilter
        }

    }
}
