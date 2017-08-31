package com.rccf.dao.impl;

import com.rccf.dao.AcceptedDao;
import com.rccf.model.Accepted;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AcceptedDaoImpl extends HibernateDaoSupport implements AcceptedDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public boolean saveOrUpdate(Accepted accepted) {
        try {
            getHibernateTemplate().saveOrUpdate(accepted);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Accepted findById(int id) {
        return getHibernateTemplate().get(Accepted.class, id);
    }
}
