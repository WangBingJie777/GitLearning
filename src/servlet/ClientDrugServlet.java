package servlet;

import bean.Drug;
import bean.Page;
import dao.DrugDao;
import service.DrugService;
import service.impl.DrugServiceImpl;
import utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

/**
 * @author wbj
 * @create 2022-08-23 22:11
 */

public class ClientDrugServlet extends BaseServlet{
    //1. 加载xml配置文件

    private final DrugService drugService = new DrugServiceImpl();

    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo、pageSize。如果这两个参数为空，则采用默认值
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2. 调用DrugService.page(pageNo,pageSize)返回Page对象--拿到数据。
        Page page = drugService.page(pageNo,pageSize);
        page.setUrl("client/drugServlet?action=page");//设置分页条的请求地址
        // 3. 将page对象保存到request域中
        request.setAttribute("page", page);
        // 4. 请求转发到/pages/client/index.jsp页面.这个页面拿到request域中的page，然后遍历输出展示到页面上---具体的输出代码在index.jsp页面里
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求的参数 pageNo、pageSize。如果这两个参数为空，则采用形参2作为defaultValue（默认值）
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = webUtils.parseInt(request.getParameter("min"),0);
        int max = webUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        // 2.调用service层的方法，得到返回Page对象
        Page page = drugService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/drugServlet?action=pageByPrice");
        //如果客户端指定了请求参数min，则追加到分页条的地址参数中
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        //如果客户端指定了请求参数max，则最佳到分页条的地址参数中
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        //通过这2此追加，就可以确保min和max参数一直回显到页面上，解决分页条部分不带价格区间的bug
        page.setUrl(sb.toString());//设置分页条的地址参数
        // 3. 保存page对象到request域中
        request.setAttribute("page", page);
        // 4. 请求转发到/pages/client/index.jsp页面。这个页面拿到request域中的page，然后遍历输出展示到页面上
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    public void pageByDrugName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        String drugName = request.getParameter("drugName");
        if(drugName!=null){
            Page page = drugService.pageByName(pageNo, pageSize, drugName);
            StringBuilder sb = new StringBuilder("client/drugServlet?action=pageByDrugName");
            //追加这个drugName参数，是为了输入框的回显
            sb.append("&drugName=").append(request.getParameter("drugName"));
            page.setUrl(sb.toString());//设置分页条的请求地址
            // 3. 保存page对象到request域中
            request.setAttribute("page", page);
            // 4. 请求转发到/pages/client/index.jsp页面。这个页面拿到request域中的page，然后遍历输出展示到页面上
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

        }
    }
}
