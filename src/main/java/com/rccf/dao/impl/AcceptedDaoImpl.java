package com.rccf.dao.impl;

import com.rccf.dao.AcceptedDao;
import com.rccf.model.AcceptProcess;
import com.rccf.model.Accepted;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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

    public Accepted findByClerk(String clerk) {
//        getHibernateTemplate()

        return null;
    }

    public boolean saveProcess(AcceptProcess process) {
        try {
            getHibernateTemplate().saveOrUpdate(process);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AcceptProcess> listProcessDetail(int accept_id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcceptProcess.class);
        criteria.add(Restrictions.eq("acceptId", accept_id));
        criteria.addOrder(Order.desc("updateTime"));
        List<AcceptProcess> processes = (List<AcceptProcess>) getHibernateTemplate().findByCriteria(criteria);
        return processes;
    }

    public AcceptProcess findProcessByid(int id) {
        return getHibernateTemplate().get(AcceptProcess.class, id);
    }

    public boolean deleteProcessByID(int id) {

        try {
            AcceptProcess process = findProcessByid(id);
            getHibernateTemplate().delete(process);
            return true;
        } catch (DataAccessException e) {
            return false;
//            e.printStackTrace();
        }
    }


}
