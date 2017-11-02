package com.rccf.service.impl;

import com.rccf.dao.RCustomerDao;
import com.rccf.model.RCustomer;
import com.rccf.service.RCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RCustomerServiceImpl implements RCustomerService {

    @Autowired
    RCustomerDao dao;


    @Override
    public RCustomer findRCustomerByID(String id) {
        return dao.findRCustomerByID(id);
    }


    @Override
    public List<RCustomer> findRCustomerByName(String name) {
        return dao.findRCustomerByName(name);
    }

    @Override
    public List<RCustomer> findRCustomerByPhone(String phone) {
        return dao.findRCustomerByPhone(phone);
    }
}
