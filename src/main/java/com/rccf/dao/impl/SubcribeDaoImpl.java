package com.rccf.dao.impl;

import com.rccf.dao.SubcribeDao;
import com.rccf.model.Subcribe;
import com.rccf.model.Unsubcribe;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by greatland on 17/7/20.
 */
@Repository
public class SubcribeDaoImpl extends HibernateDaoSupport implements SubcribeDao {


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void saveSubcribe(Subcribe subcribe) {
        getHibernateTemplate().saveOrUpdate(subcribe);
    }

    public void saveUnsubcribe(Unsubcribe unsubcribe) {
        getHibernateTemplate().saveOrUpdate(unsubcribe);
    }

    public List<Subcribe> listSubcriberByPage(int offset, int length) {
        List<Subcribe> entitylist = null;
        try {
            Query query = getSessionFactory().getCurrentSession().createQuery("from Subcribe");
            query.setFirstResult(offset);
            query.setMaxResults(length);
            entitylist = query.list();

        } catch (RuntimeException re) {
            throw re;
        }
        return entitylist;
    }

    public List<Unsubcribe> listUnsubcribeByPage(int offset, int length) {

        List<Unsubcribe> entitylist = null;
        try {
            Query query = currentSession().createQuery("from Unsubcribe ");
            query.setFirstResult(offset);
            query.setMaxResults(length);
            entitylist = query.list();

        } catch (RuntimeException re) {
            throw re;
        }
        return entitylist;

    }
}
