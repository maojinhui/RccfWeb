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

    void saveUser(User user);

    User findUserById(String id);

    User findUserByPhone(String phone);

    User findUserByName(String name);

    List<User> getUsers(Page page);

}
