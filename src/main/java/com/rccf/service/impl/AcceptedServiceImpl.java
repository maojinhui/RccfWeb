package com.rccf.service.impl;

import com.rccf.dao.AcceptedDao;
import com.rccf.model.AcceptProcess;
import com.rccf.model.Accepted;
import com.rccf.service.AcceptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Accepted findByClerk(String clerk) {
        return null;
    }

    public boolean saveProcess(AcceptProcess process) {
        return dao.saveProcess(process);
    }

    public List<AcceptProcess> listProcessDetail(int accept_id) {
        return dao.listProcessDetail(accept_id);
    }

    public AcceptProcess findProcessByid(int id) {
        return dao.findProcessByid(id);
    }
}
