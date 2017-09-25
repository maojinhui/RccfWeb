package com.rccf.dao;

import com.rccf.component.Page;
import com.rccf.model.Test;
import com.rccf.model.User;

import java.util.List;

/**
 * Created by greatland on 17/7/10.
 */
public interface UserDao {

    void save(Test test);

    boolean saveUser(User user);

    boolean deleteUser(User user);

    User findUserById(String id);

    User findUserByPhone(String phone);

    User findUserByName(String name);

    User findUserBuOpenid(String openid);

    List<User> getUsers(Page page);

}
