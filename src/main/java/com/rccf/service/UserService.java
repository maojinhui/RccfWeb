package com.rccf.service;

import com.rccf.component.Page;
import com.rccf.dao.UserDao;
import com.rccf.model.Test;
import com.rccf.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by greatland on 17/7/10.
 */
public interface UserService {


    void save(Test test);

    boolean saveUser(User user);

    User findUserById(String id);

    User findUserByPhone(String phone);

    User findUserByName(String name);

    User findUserByOpenid(String openid);

    List<User> getUsers(Page p);

    boolean deleteUser(User user);

}
