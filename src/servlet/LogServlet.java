package servlet;

import bean.DrugLog;
import bean.EmpLog;
import bean.Order;
import service.DrugLogService;
import service.EmpLogService;
import service.impl.DrugLogServiceImpl;
import service.impl.EmpLogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**用来处理药品日志、员工日志相关的请求。
 * @author wbj
 * @create 2022-08-30 22:07
 */
public class LogServlet extends BaseServlet{
    private DrugLogService drugLogService = new DrugLogServiceImpl();
    private EmpLogService empLogService = new EmpLogServiceImpl();

    //展示日志
    public void showDrugLogs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //调用Service层获得多有的药品日志
        List<DrugLog> drugLogs = drugLogService.showDrugLogs();
        //保存到request域
        req.setAttribute("drugLogs",drugLogs);
        //请求转发
        req.getRequestDispatcher("/pages/manager/drug_log.jsp").forward(req, resp);
    }

    public void showEmpLogs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmpLog> empLogs = empLogService.showEmpLogs();
        req.setAttribute("empLogs",empLogs);
        req.getRequestDispatcher("/pages/manager/emp_log.jsp").forward(req, resp);

    }
}
