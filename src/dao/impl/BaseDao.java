package dao.impl;

import utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/** 抽象类，作为父类--供继承使用，这样可以复用代码。
 * @author wbj
 * @date 2022/1/20 - 17:07
 *
 * 2022年7月31日 19:58
 * ①因为加入了事务，jdbcUtils类修改了，不能在BaseDao里关闭连接了，所以，删除finally里的代码。
 * 改好后，我们就能确保操作是用同一个Connection对象。这个，看jdbcUtils.getConnetion()就能知道，每次
 * 都是先从ThreadLocal conns 中取，如果没有，则从线程池中取，并保存到conns中，供此线程后面的操作使用。
 * 如果conns中有之前保存的连接，则直接用。
 *
 * ②将异常抛出，不在BaseDao里抛出。原因仍是因为事务的加入，如果这里将异常捕获，在他基础上的其他操作（后面的人）就
 * 不知道BaseDao里发生了异常，就无法知道什么时候回滚。所以，必须抛出异常，让后面的人捕获，让后面的人知道
 * 从而可以有效回滚事务。以后写代码要这样做。
 *
 * 这里，因为是后期修改，所以，为了省事，即捕获了又手动抛出了，如果直接抛出，会导致很多关联问题，很多bug。
 * 但是要记住，以后，要直接抛出，就是写在方法声明后面的那个throws。

 *
 *
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库。IDEA提示我把queryRunner弄成final的。
    //org.apache.commons.dbutils.QueryRunner 使用commons-dbutils这个工具jar包
    private final QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行：增insert、删delete、改update这3种sql语句
     * 传入的形参也供queryRunner.update（）使用
     * @return 若返回-1，说明执行失败。返回其他值，表示影响的行数
     * */
    public int update(String sql,Object...args){
        //System.out.println("BaseDao程序在【"+ Thread.currentThread().getName()+"】中");

        // 只需要调用时候传sql语句，以及？的值所组成的数组
        Connection connetion = jdbcUtils.getConnetion();
        try {
            return queryRunner.update(connetion,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type 返回的 对象的类型
     * @param sql sql语句
     * @param args ？的值
     * @param <T> 返回的类型的泛型
     * @return 单个javaBean对象
     * 泛型方法。方法用泛型是因为造方法时，其形参类型和返回值类型不确定，所以用了泛型，泛型值的确定是在调用的时候确定的
     * 这也是一种通用性的体现。
     * public后的<T>是泛型方法声明
     *  Class<User> userClass = User.class;
     *  System.out.println(userClass);//class com.atwbj.bean.User
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection connetion = jdbcUtils.getConnetion();
        try {
            //BeanHandler对象是结果集，将查询到结果封到里面
            return queryRunner.query(connetion,sql,new BeanHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 查询返回 多个 javaBean的sql语句
     * @param type 返回的 对象的类型
     * @param sql sql语句
     * @param args ？的值
     * @param <T> 返回的类型的泛型
     * @return 多个javaBean对象组成的List
     */
    // public后的<T>是泛型方法声明
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
        Connection connetion = jdbcUtils.getConnetion();
        try {
            return queryRunner.query(connetion,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一行一列的sql语句。通常就是统计。
     * @param sql sql语句
     * @param args ？的值
     * @return 单个值
     * Scalar adj：数量的，标量的；n：数量，标量
     * Handler处理者，处理器，经理人
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection connetion = jdbcUtils.getConnetion();
        try {
            return queryRunner.query(connetion,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
