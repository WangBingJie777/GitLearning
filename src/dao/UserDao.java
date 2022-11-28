package dao;

import bean.User;

/**
 * @author wbj
 * @create 2022-08-25 20:03
 */
public interface UserDao {

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 若返回null，则数据库中无此用户。反之，返回User对象
     */
    public User queryUserByName(String username);


    /**
     * 保存用户信息
     * @param user 用户对象
     * @return 若-1，则操作失败。其他值，是sql语句影响的行数
     * 详解：添加操作失败的情况有：①主键重复 ②unique字段重复
     */
    public int saveUser(User user);


    /**
     * 通过用户名和 密码查
     * @param username 用户名
     * @param password 密码
     * @return 若返回null，则用户名或密码错误。
     */
    public User queryByNameAndPassword(String username,String password);

}
