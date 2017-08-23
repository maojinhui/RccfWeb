package com.rccf.service.impl;

import com.rccf.dao.BankRateDao;
import com.rccf.model.BankLoanRate;
import com.rccf.service.BankRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankRateServiceImpl implements BankRateService {


    @Autowired
    BankRateDao dao;

    public void save(BankLoanRate rate) {
        dao.save(rate);
    }

    public BankLoanRate findLastRate() {
        return dao.findLastRate();
    }
}
