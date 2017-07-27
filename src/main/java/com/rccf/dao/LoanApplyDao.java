package com.rccf.dao;

import com.rccf.component.Page;
import com.rccf.model.Loanapply;

import java.util.List;

public interface LoanApplyDao {

    Loanapply getLoanapplyByID(String id);

    boolean save(Loanapply loanapply);

    List<Loanapply> list(Page page);

}
