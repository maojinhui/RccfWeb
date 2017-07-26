package com.rccf.service.impl;

import com.rccf.component.Page;
import com.rccf.dao.UserDao;
import com.rccf.model.Test;
import com.rccf.model.User;
import com.rccf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by greatland on 17/7/10.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    public void save(Test test) {
        dao.save(test);

    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public List<User> getUsers(Page page) {
        return dao.getUsers(page);
    }
}
