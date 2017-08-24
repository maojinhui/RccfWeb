package com.rccf.dao.impl;

import com.rccf.dao.ProductDao;
import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public ProductDiya findProductDiyaByID(int id) {
        return getHibernateTemplate().get(ProductDiya.class, id);
    }

    public ProductZhiya findProductZhiyaByID(int id) {
        return getHibernateTemplate().get(ProductZhiya.class, id);
    }
}
