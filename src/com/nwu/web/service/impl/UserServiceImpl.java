package com.nwu.web.service.impl;

import com.nwu.web.dao.UserDao;
import com.nwu.web.dao.impl.UserDaoImpl;
import com.nwu.web.pojo.User;
import com.nwu.web.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userdao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userdao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userdao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userdao.queryUserByUsername(username) == null) {
            return false;
        } else {
            return true;
        }
    }
}
