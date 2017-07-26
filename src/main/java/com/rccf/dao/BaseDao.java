package com.rccf.dao;

import com.rccf.component.Page;
import com.rccf.model.User;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

    /**
     * 查询某个表的总数
     *
     * @param clazz
     * @return
//     */
//    public int getCount(Class clazz) {
//        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);
//        criteria.setProjection(Projections.rowCount());
//        int count = (Integer)criteria.uniqueResult();
//        return count;
//    }

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

//        return getHibernateTemplate().execute(new HibernateCallback<List>() {
//            public List doInHibernate(Session session) throws HibernateException {
//                Criteria c = detachedCriteria.getExecutableCriteria(session);
//                page.setTotalCount((Integer) c.setProjection(Projections.rowCount()).uniqueResult());
//                c.setProjection(null);
//                c.setResultTransformer(Criteria.ROOT_ENTITY);
//                c.setFirstResult((int) page.getBeginIndex());
//                c.setMaxResults(page.getEveryPage());
//                return c.list();
//            }
//        });

    }

}
