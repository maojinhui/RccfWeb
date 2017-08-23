package com.rccf.service;

import com.rccf.model.BankLoanRate;

public interface BankRateService  {

    void save(BankLoanRate rate);

    BankLoanRate findLastRate();

}
