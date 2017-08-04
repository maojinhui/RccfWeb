package com.rccf.dao.impl;

import com.rccf.component.Page;
import com.rccf.dao.BaseDao;
import com.rccf.dao.UserDao;
import com.rccf.model.Test;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BaseDao baseDao;

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void save(Test test) {
        getHibernateTemplate().save(test);
    }

    public void saveUser(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public User findUserById(String id) {
        return getHibernateTemplate().get(User.class,id);
    }

    public User findUserByPhone(String phone) {
        User user = null;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("phone",phone));
        List<?> byCriteria = getHibernateTemplate().findByCriteria(detachedCriteria);
        if (null != byCriteria && byCriteria.size()>0){
            user = (User) byCriteria.get(0);
        }
        return user;
    }

    public User findUserByName(String name) {
        List<?> objects = getHibernateTemplate().find("from User where userName = ?", name);
        if (null != objects && objects.size()>0){
            return (User) objects.get(0);
        }
        return null;
    }


    public User findUserBuOpenid(String openid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("openid",openid));
        List<?> byCriteria = getHibernateTemplate().findByCriteria(detachedCriteria);
        User user = null;
        if (null != byCriteria && byCriteria.size()>0){
            user = (User) byCriteria.get(0);
        }
//        User user = (User) getHibernateTemplate().findByNamedQuery("from User where phone = ?", phone).get(0);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers(final Page page) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
            public List<User> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from User");
                //设置参数
//                query.setParameter(0, username);
                //设置每页显示多少个，设置多大结果。
                query.setMaxResults(page.getEveryPage());
                //设置起点
                query.setFirstResult(page.getBeginIndex());
                return query.list();
            }
        });
    }


}
