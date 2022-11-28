package dao.impl;

import bean.User;
import dao.UserDao;

/**
 * @author wbj
 * @create 2022-08-25 20:03
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByName(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ";
        return queryForOne(User.class,sql,username);//queryForOne()是父类的方法，可以直接用

    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO users(`username`,`password`,`email`) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }


    @Override
    public User queryByNameAndPassword(String username, String password) {
        String sql = " SELECT * FROM users WHERE username =? AND password =?";
        return queryForOne(User.class,sql,username,password);//queryForOne()是父类的方法，可以直接用
    }
}
