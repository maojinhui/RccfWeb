package com.rccf.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BaseDao extends HibernateDaoSupport {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 查询某个表的总数
     * @param clazz
     * @return
     */
    public long getCount(Class clazz){
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        return count;
    }

    /**
     * 根据条件查询总数
     * @param clazz
     * @param detachedCriteria
     * @return
     */
    public long getCount(Class clazz,DetachedCriteria detachedCriteria){
        detachedCriteria.setProjection(Projections.rowCount());
        Criteria criteria = detachedCriteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
        Long count = (Long) criteria.uniqueResult();
        return count;
    }



}
