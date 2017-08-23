package com.rccf.dao.impl;

import com.rccf.dao.BankRateDao;
import com.rccf.model.BankLoanRate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BankLoanRateDaoImpl extends HibernateDaoSupport implements BankRateDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public void save(BankLoanRate rate) {
        getHibernateTemplate().saveOrUpdate(rate);
    }

    public BankLoanRate findLastRate() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BankLoanRate.class);
        detachedCriteria.addOrder(Order.desc("time"));
        List<?> byCriteria = getHibernateTemplate().findByCriteria(detachedCriteria);
        return (BankLoanRate) byCriteria.get(0);
    }
}
