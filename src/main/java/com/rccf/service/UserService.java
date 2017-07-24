package com.rccf.service;

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

    void saveUser(User user);

    List<User> getUsers();

}
