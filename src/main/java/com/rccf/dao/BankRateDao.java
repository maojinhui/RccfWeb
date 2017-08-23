package com.rccf.dao;

import com.rccf.model.BankLoanRate;

public interface BankRateDao {

    void save(BankLoanRate rate);

    BankLoanRate findLastRate();
}
