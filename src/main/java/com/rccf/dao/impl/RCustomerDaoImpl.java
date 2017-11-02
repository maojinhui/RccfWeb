package com.rccf.dao.impl;

import com.rccf.dao.RCustomerDao;
import com.rccf.model.RCustomer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RCustomerDaoImpl extends HibernateDaoSupport implements RCustomerDao {


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public RCustomer findRCustomerByID(String id) {
        return getHibernateTemplate().get(RCustomer.class, id);
    }

    @Override
    public List<RCustomer> findRCustomerByName(String name) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RCustomer.class);
        detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        List<RCustomer> rCustomers = (List<RCustomer>) getHibernateTemplate().findByCriteria(detachedCriteria);
        return rCustomers;
    }

    @Override
    public List<RCustomer> findRCustomerByPhone(String phone) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RCustomer.class);
        detachedCriteria.add(Restrictions.like("phone", phone, MatchMode.ANYWHERE));
        List<RCustomer> rCustomers = (List<RCustomer>) getHibernateTemplate().findByCriteria(detachedCriteria);
        return rCustomers;
    }
}
