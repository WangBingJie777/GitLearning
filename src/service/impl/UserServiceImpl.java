package service.impl;

import bean.User;
import dao.impl.UserDaoImpl;
import service.UserService;

/**
 * @author wbj
 * @create 2022-08-25 20:43
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao =  new UserDaoImpl();
    @Override
    public int registUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByName(username)==null)
        {
            return false;// 为null，则没查到，说明不存在，
        }
        return true;
    }
}
