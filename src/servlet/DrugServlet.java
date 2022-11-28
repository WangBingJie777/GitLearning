package servlet;

import bean.Drug;
import bean.Page;
import service.DrugService;
import service.impl.DrugServiceImpl;
import utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**在web.xml中配置为：<url-pattern>/manager/drugServlet</url-pattern>
 * @author wbj
 * @create 2022-08-26 21:46
 *
 *  //不能用请求转发，会导致刷新后重复提交表单，要用请求重定向
 *         //请求重定向的/表示到端口号（与上面的/有区别），前面缺工程名，所以前面要加上工程名
 *         //一定要认真检查路径，之前在"后敲了一个空格，就寄了
 *         //因为采用了分页，所以，这要修改为action=page
 *         //pageNo= 是请求参数，这个是为了添加后，能跳到最后一页，即让用户看到新记录所在页
 */
public class DrugServlet extends BaseServlet{
    private DrugService drugService = new DrugServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求的参数，封装成Drug对象
        Drug drug = webUtils.copyParamToBean(request.getParameterMap(), new Drug());
        //2.保存到数据库
        int i = drugService.addDrug(drug);
        request.setAttribute("i", i);
        //3.跳到药品列表页面  /manager/drugServlet?action=list 。 最前面的/表示到IDEA中的*web目录下
        response.sendRedirect(request.getContextPath()+"/manager/drugServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数：药品id
        String id = request.getParameter("id");
        //2.删除
        drugService.delDrug(webUtils.parseInt(id, 0));
        //3.【重定向】回药品列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/drugServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数==封装成Drug对象
        String id =  request.getParameter("id");
        Drug drug = new Drug();
        //getParameterMap把表单里的参数也注入到drug对象中
        drug= webUtils.copyParamToBean(request.getParameterMap(), drug);
        drug.setDrug_id(webUtils.parseInt(id,0));
        //2.更新
        int i = drugService.updateDrug(drug);
        request.setAttribute("i", i);
        //3.【重定向】回药品列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/drugServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    //因为有了分页方法，即page方法，所以这个方法不再使用了。
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询全部药品
        List<Drug> drugs = drugService.queryDrugs();
        //2.保存到request域中
        request.setAttribute("drugs", drugs);
        //3.把请求转发到/pages/manager/drug_manager.jsp页面 .第一个/表示映射到*web下。
        //页面拿到request域中的数据，然后遍历输出展示到页面上---具体的代码在drug_manager.jsp中写
        request.getRequestDispatcher("/pages/manager/drug_manager.jsp").forward(request, response);
    }
    /**
     * 用于drug_edit.jsp页面，修改表单的回显。就是用户直接在原数据上修改。能做到这样，就是因为有咱默默地写过去。
     * 下面做的事情，就是当用户点击修改时，把要修改的药品信息先显示到drug_edit中的表单里。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数：药品编号
        String id = request.getParameter("id");
        //2.调用DrugService中的findDrugByID()得到药品--这就是要修改的药品
        Drug drugById = drugService.findDrugById(webUtils.parseInt(id, 0));
        //3.将药品保存到request中
        request.setAttribute("drug", drugById);
        //4.请求转发到/pages/manager/drug_edit.jsp页面。这个页面拿到request域中的page，然后遍历输出展示到页面上
        request.getRequestDispatcher("/pages/manager/drug_edit.jsp").forward(request, response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo、pageSize
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2. 调用DrugService.page(pageNo,pageSize)返回Page对象
        Page<Drug> page = drugService.page(pageNo, pageSize);
        page.setUrl("manager/drugServlet?action=page");
        // 3. 将page对象保存到request域中
        request.setAttribute("page", page);
        // 4. 请求转发到/pages/manager/drug_manager.jsp页面，这个页面拿到request域中的page，然后遍历输出展示到页面上
        request.getRequestDispatcher("/pages/manager/drug_manager.jsp").forward(request, response);
    }
}
