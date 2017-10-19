package com.rccf.dao.impl;

import com.rccf.dao.DocumentDao;
import com.rccf.model.EmployeeDocuments;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class DocumentDaoImpl extends HibernateDaoSupport implements DocumentDao {


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    public EmployeeDocuments findDocumentById(int id) {
        return getHibernateTemplate().get(EmployeeDocuments.class, id);
    }

    public EmployeeDocuments findDocumentByeID(String eid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeDocuments.class);
        criteria.add(Restrictions.eq("eid", eid));
        List<EmployeeDocuments> documents = (List<EmployeeDocuments>) getHibernateTemplate().findByCriteria(criteria);
        if (null != documents && documents.size() > 0) {
            return documents.get(0);
        }
        return null;
    }


}
