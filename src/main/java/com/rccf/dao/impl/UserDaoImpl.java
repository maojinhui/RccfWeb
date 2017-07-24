package com.rccf.dao.impl;

import com.rccf.dao.UserDao;
import com.rccf.model.Test;
import com.rccf.model.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by greatland on 17/7/10.
 */
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void save(Test test) {
        getHibernateTemplate().save(test);
    }

    public void saveUser(User user) {
        getHibernateTemplate().save(user);
    }

    public List<User> getUsers() {
        return null;
    }


}
