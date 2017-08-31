package com.rccf.service.impl;

import com.rccf.dao.AcceptedDao;
import com.rccf.model.Accepted;
import com.rccf.service.AcceptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptedServiceImpl implements AcceptedService {

    @Autowired
    AcceptedDao dao;

    public boolean saveOrUpdate(Accepted accepted) {
        return dao.saveOrUpdate(accepted);
    }

    public Accepted findById(int id) {
        return dao.findById(id);
    }
}
