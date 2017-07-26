package com.rccf.dao.impl;

import com.rccf.component.Page;
import com.rccf.dao.LoanApplyDao;
import com.rccf.model.Loanapply;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanApplyDaoImpl extends HibernateDaoSupport implements LoanApplyDao {

    @Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public Loanapply getLoanapplyByID(String id) {
        return getHibernateTemplate().load(Loanapply.class,id);
    }

    public void save(Loanapply loanapply) {
        getHibernateTemplate().saveOrUpdate(loanapply);
    }

    public List<Loanapply> list(Page page) {
    return null;
    }
}
