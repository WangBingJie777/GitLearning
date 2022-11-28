package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**2022年8月27日 18:08，将jabcUtils类修改成了支持事务的了。其实就是把连接对象放到ThreadLocal中。
 * @author wbj
 * @date 2022/8/27 - 14:53
 */


public class jdbcUtils {
    private static DruidDataSource dataSource=null;//创建一个连接池对象，初始为null，后面会把他的实体创建出来，然后使用。
   //ThreadLocal给当前线程关联一个数据，可以是集合、数组、普通变量、对象。
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    //    使用静态代码块来初始化
    static {
        // 读取jdbc.properties属性配置文件
        InputStream resourceAsStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        try {
            //从流中加载key-value给properties
            properties.load(resourceAsStream);
            //创建连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取数据库连接池中的连接
     */
    public static  Connection getConnetion(){
        Connection conn = conns.get();
        try {
            if(conn == null){
                //如果conns中没有连接，就直接从数据库连接池中取，并保存到conns中,之后，同一个线程里的其他操作，若
                //用到连接对象，则就从conns中取。这就确保了一个线程中的所有操作都是用同一个连接对象，为事务的使用提供前提条件
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);//设置为手动管理事务
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param conn
     */

    /** 不再使用
    public static void closeConn(Connection conn)
    {
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }**/
    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){//不为null，说明 之前使用过该连接
            try {
                conn.commit();//提价事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        //提交事务并关闭释放连接后，一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollBackAndClose(){
        {
            Connection conn = conns.get();
            if(conn != null){//不为null，说明 之前使用过该连接
                try {
                    conn.rollback();//回滚事务
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();//关闭连接
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
            //提交事务并关闭释放连接后，一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
            conns.remove();
        }

    }
}
