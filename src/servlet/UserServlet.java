package servlet; /**
 * @author wbj
 * @date 2022/7/22 - 14:55
 *
 * UserServlet里的每一个方法，都代表一个“功能”（就是咱需求分析里所说的功能）。
 * 其他xxxServlet也是如此。
 */


import bean.Emp;
import bean.Manager;
import bean.User;
import com.google.gson.Gson;
import service.impl.EmpServiceImpl;
import service.impl.ManagerServiceImpl;
import service.impl.UserServiceImpl;
import utils.webUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {
    // 因为继承了BaseServlet，所以，doPost在此类中也是有的。你就想着，这个类里也有一份doPost
    private UserServiceImpl userService = new UserServiceImpl();
    private EmpServiceImpl empService = new EmpServiceImpl();

    private ManagerServiceImpl managerService = new ManagerServiceImpl();

    /**
     * 这个登录功能，包括了客户和管理员的登录
     * 客户使用用户名和密码登录。管理员使用自己的手机号和密码登录。所以username既可以作为用户名，也可以充当手机号。
     *

     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {// 1. 获取请求参数  。也就是表单中的信息
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            // 2.调用userService.login()来处理登录业务。只需要用户名和密码，其他都填null
            User login = userService.login(new User( null,username, password, null));
            // Emp empLogin = empService.empLogin(username,password);
            Manager managerLogin = managerService.managerLogin(username, password);
            // 3 判断登录是否成功.分2大类：管理员or客户。
            //客户
            if("customer".equals(role)){
                if(login == null) {
                    // 失败。跳到登录页面。把错误信息和回显的表单信息，保存到request域中
                    req.setAttribute("loginErrorMessage", "用户名或密码错误！");
                    req.setAttribute("username", username);
                    req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);//将请求转发
                }else{
                    //成功。跳到首页
                    //将用户登录信息对象保存到session域中，范围足够大，可以用在多个页面的回显。request域太小，不能满足此需求。
                    req.getSession().setAttribute("user",login);
                    req.getSession().setAttribute("role",role);//保存登录时是用户还是管理者
                    req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);//将请求转发
                }
            }else{
                //管理员
                if(managerLogin == null){
                    // 失败。跳到登录页面
                    //把错误信息和回显的表单信息，保存到request域中
                    req.setAttribute("loginErrorMessage", "用户名或密码错误！");
                    req.setAttribute("username", username);
                    req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);//将请求转发
                }else{
                    //成功。跳到管理员页面
                    req.getSession().setAttribute("user",managerLogin);
                    req.getSession().setAttribute("role",role);//保存登录时是用户还是管理者
                    req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req,resp);//将请求转发
                }
            }
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中用户登录的信息（或直接销毁Session）。这里是直接销毁Session对象
        req.getSession().invalidate();
        //2.重定向到首页（或登录页面）.这里是重定向到首页
        resp.sendRedirect(req.getContextPath());

        }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            //获取Session中的验证码（服务器中的）.这个key是kapacha这个jar包中的程序定义的。
            String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            //获取完后，马上删除Session中的验证码，防止用户再次使用
            request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            //获取表单中的验证码（这是用户填写的）
            String code = request.getParameter("code");
            //表单被提交给服务器。
            //1. 获取【请求】参数，也就是表单中的信息
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            //webUtils测试
            User user = webUtils.copyParamToBean(request.getParameterMap(), new User());
            //2.检查验证码是否正确
            if (token!=null && token.equalsIgnoreCase(code)) {
                // 正确。
                // 3.检查用户名是否可用.  （回顾： 用户名必须由字母，数字下划线组成，长度为 5 到 12 位）
                if (userService.existUsername(username)) {
                    // 不可用, 跳回到注册页面，重新注册
                    System.out.println("该用户名不可用，已存在");
                    request.setAttribute("registErrorMsg", "该用户名已存在,您不可用");
                    request.setAttribute("username",username);
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);//将请求转发
                } else {
                    // 可用。
                    // 使用userService保存到数据库，然后跳到注册成功页面
                    userService.registUser(new User( null,username, password, email));
                    request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);//将请求转发
                }
            } else {
                // 不正确。跳回到注册页面，重新注册。   / 开头，表示到web
                //  Dispatcher 调度员、分配器
                //System.out.println("验证码错误");
                // 注册失败，回写一些表单数据，不用让用户重填了.
                // 把回显信息保存到request中，然后，在jsp中写代码输出到前端页面上
                request.setAttribute("registErrorMsg", "验证码错误");
                request.setAttribute("username",username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        }
    }

    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数username
        String username = req.getParameter("username");
        //调用userService.existUsername()
        boolean existUsername = userService.existUsername(username);
        // 把响应的结果封装成map对象：键值对
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("existUsername", existUsername);
        //map对象转为JSON字符串
        Gson gson = new Gson();
        String jsonString = gson.toJson(resMap);
        //通过响应的字符流向前端返回jsonString
        resp.getWriter().write(jsonString);
    }
}
