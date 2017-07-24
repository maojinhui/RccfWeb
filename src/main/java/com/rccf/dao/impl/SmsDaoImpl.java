package com.rccf.dao.impl;

import com.rccf.dao.SmsDao;
import com.rccf.model.Sms;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by greatland on 17/7/14.
 */
@Repository
public class SmsDaoImpl extends HibernateDaoSupport implements SmsDao {


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void save(Sms sms) {
        getHibernateTemplate().save(sms);
    }

    public List<Sms> list() {
        return null;
    }

    public List<Sms> list(int pageNo, int pageCount) {
        return null;
    }

    public List<Sms> list(int pageNo, int pageCount, int code) {
        return null;
    }
}
