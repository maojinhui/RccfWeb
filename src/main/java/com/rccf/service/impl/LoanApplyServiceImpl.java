package com.rccf.service.impl;

import com.rccf.component.Page;
import com.rccf.dao.LoanApplyDao;
import com.rccf.model.Loanapply;
import com.rccf.service.LoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplyServiceImpl implements LoanApplyService{

    @Autowired
    private LoanApplyDao loanApplyDao;


    public Loanapply getLoanapplyByID(int id) {
        return loanApplyDao.getLoanapplyByID(id);
    }

    public boolean save(Loanapply loanapply) {
        return loanApplyDao.save(loanapply);
    }

    public List<Loanapply> list(Page page) {
        return loanApplyDao.list(page);
    }
}
