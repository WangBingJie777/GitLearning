package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**使用Filter拦截/pages/manager下的所有内容，只有登录后才可访问，实现权限检查。
 * @author wbj
 * @date 2022/8/29 10:54
 */
public class ManagerFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        Object user = req.getSession().getAttribute("user");
        String  role = (String) req.getSession().getAttribute("role");

        if(user == null){
            //未登录.给用户转到登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else if(role.equals("manager")){
            //已经登录且是管理员，则放行。
            chain.doFilter(request, response);
        }else if(role.equals("customer")){
            //先保存属性再转发
            String s = "若想进行后台管理，请使用管理员账号登录!";

            req.setAttribute("managerMsg",s);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

        }
    }

    @Override
    public void destroy() {

    }

}
