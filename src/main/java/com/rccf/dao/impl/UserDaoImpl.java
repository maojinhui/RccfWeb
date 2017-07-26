package com.rccf.dao.impl;

import com.rccf.component.Page;
import com.rccf.dao.UserDao;
import com.rccf.model.Test;
import com.rccf.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
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

    public List<User> getUsers(final Page page) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
            public List<User> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from User");
                //设置参数
//                query.setParameter(0, username);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(page.getEveryPage());
                //设置起点
                query.setFirstResult((int)page.getBeginIndex());
                return query.list();
            }
        });
    }


}
