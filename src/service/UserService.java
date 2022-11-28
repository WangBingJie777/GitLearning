package service;

import bean.User;

/**业务层。一个业务一个方法。
 * 注册业务、登录业务、注册时检查用户名是否存在业务
 * @author wbj
 * @create 2022-08-25 20:18
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return
     */
    public int registUser(User user);


    /**
     * 登录
     * @param user 用户对象
     * @return 返回null，登录失败。返回有值，登录成功
     */
    public User login(User user);
    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 返回true 用户名已经存在； false 用户名不存在，可以注册。
     */
    public boolean existUsername(String username);
}
