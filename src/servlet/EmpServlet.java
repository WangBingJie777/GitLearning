package servlet;

import bean.Emp;
import bean.Page;
import service.EmpService;
import service.impl.EmpServiceImpl;
import utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wbj
 * @create 2022-08-29 14:22
 */
public class EmpServlet extends BaseServlet{
    private EmpService empService = new EmpServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求的参数，封装成Emp对象
        Emp emp = webUtils.copyParamToBean(request.getParameterMap(), new Emp());
        //2.保存到数据库
        int i = empService.addEmp(emp);
        request.setAttribute("i", i);
        //3.跳到员工列表页面 。 最前面的/表示到IDEA中的*web目录下
        //不能用请求转发，会导致刷新后重复提交表单，要用请求重定向
        //请求重定向的/表示到端口号（与上面的/有区别），前面缺工程名，所以前面要加上工程名
        //一定要认真检查路径，之前在"后敲了一个空格，就寄了
        //因为采用了分页，所以，这要修改为action=page
        //pageNo= 是请求参数，这个是为了添加后，能跳到最后一页，即让用户看到新记录所在页
        response.sendRedirect(request.getContextPath()+"/manager/empServlet?action=page&pageNo="+pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数：员工的id
        String id = request.getParameter("id");
        //2.删除
        empService.delEmp(webUtils.parseInt(id, 0));
        //3.【重定向】回药品员工列表页面
        //因为采用了分页，所以，这要修改为action=page
        response.sendRedirect(request.getContextPath()+"/manager/empServlet?action=page&pageNo="+request.getParameter("pageNo"));

    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数==封装成Emp对象
        String id =  request.getParameter("id");
        Emp emp = new Emp();
        //getParameterMap把表单里的参数也注入到emp对象中
        emp= webUtils.copyParamToBean(request.getParameterMap(), emp);
        //不知道为啥，使用webUtils里的方法，不能把id注入。所有只能手动设置一下了
        emp.setEmp_id(webUtils.parseInt(id,0));

        //2.更新
        int i = empService.updateEmp(emp);
        request.setAttribute("i", i);
        //3.【重定向】回药员工列表页面
        response.sendRedirect(request.getContextPath()+"/manager/empServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * 用于emp_edit.jsp页面，修改表单的回显。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数：员工的id号
        String id = request.getParameter("id");
        //2.调用empService中的findEmpByID()得到员工--这就是要修改的员工
        Emp empById = empService.findEmpById(webUtils.parseInt(id, 0));
        //3.将药品保存到request中
        request.setAttribute("emp", empById);
        //4.请求转发到/pages/manager/emp_edit.jsp页面。这个页面拿到request域中的page，然后遍历输出展示到页面上
        request.getRequestDispatcher("/pages/manager/emp_edit.jsp").forward(request, response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo、pageSize
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2. 调用EmpService.page(pageNo,pageSize)返回Page对象
        Page<Emp> page = empService.page(pageNo, pageSize);
        page.setUrl("manager/empServlet?action=page");
        // 3. 将page对象保存到request域中
        request.setAttribute("page", page);
        // 4. 请求转发到/pages/manager/emp_manager.jsp页面，这个页面拿到request域中的page，然后遍历输出展示到页面上
        request.getRequestDispatcher("/pages/manager/emp_manager.jsp").forward(request, response);
    }
}
