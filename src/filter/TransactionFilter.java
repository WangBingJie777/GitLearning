package filter;

import utils.jdbcUtils;

import utils.jdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**事务过滤器--TransactionFilter。别忘了到web.xml中配置。
 * 在web.xml配置后，就可以给所有的Service加上事务管理，当底层出现了异常后，就向上抛，抛到这，统一回滚，然后在这把异常继续向上抛，
 * 抛给Tomcat服务器，进行统一展示友好的错误页面
 * @author wbj
 * @date 2022/8/28 - 8:52
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
            //一切顺利，提交事务并关闭连接。
            jdbcUtils.commitAndClose();//import com.atwbj.utils.jdbcUtils;

        } catch (Exception e) {//这里修改成最大的异常类型，什么异常都可以捕获
            //出现异常，回滚事务并关闭连接
            jdbcUtils.rollBackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//继续向上抛，把异常抛给Tomcat服务器，进行统一展示友好的错误页面
            //错误页面需要在web.xml中配置。

        }
    }

    @Override
    public void destroy() {

    }
}
