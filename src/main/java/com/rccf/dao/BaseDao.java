package com.rccf.dao;

import com.rccf.component.Page;
import com.rccf.model.User;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
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

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }


    public boolean save(Object o) {
        try {
            getHibernateTemplate().saveOrUpdate(o);
//            getHibernateTemplate().evict(0);
            this.getHibernateTemplate().flush();
            this.getHibernateTemplate().clear();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object o) {
        try {
            getHibernateTemplate().delete(o);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据条件查询总数
     *
     * @param detachedCriteria
     * @return
     */
    public int getCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        Criteria criteria = detachedCriteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
        String result = criteria.uniqueResult().toString();
        return Integer.valueOf(result);
    }

    public List getList(final Page page, final DetachedCriteria detachedCriteria) {

        Criteria criteria= detachedCriteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
//        page.setTotalCount((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult());
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);
        criteria.setFirstResult(page.getBeginIndex());
        criteria.setMaxResults(page.getEveryPage());
        return criteria.list();


    }


    public List getList(DetachedCriteria detachedCriteria) {
        return getHibernateTemplate().findByCriteria(detachedCriteria);
    }


    public List queryBySql(String sql) {
        List<Object[]> list = getSession().createSQLQuery(sql).list();
        return list;
    }

    public List queryBySqlFormatObject(String sql) {
        List<Object> list = getSession().createSQLQuery(sql).list();
        return list;
    }

    public String queryMaxBySql(String sql) {
//        getSession().createQuery("SELECT MAX(a.acceptedNumber)  FROM Accepted a")
        return (String) getSession().createQuery(sql).uniqueResult();
    }

    public List queryBySqlFormatClass(String sql, Class clazz) {
        List list = getSession().createSQLQuery(sql).addEntity(clazz).list();
        return list;
    }


    public boolean excuteSql(String sql) {
        try {
            SQLQuery sqlQuery = getSession().createSQLQuery(sql);
            sqlQuery.executeUpdate();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

}
