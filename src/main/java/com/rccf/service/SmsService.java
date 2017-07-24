package com.rccf.service;

import com.rccf.model.Sms;

import java.util.List;

/**
 * Created by greatland on 17/7/14.
 */
public interface SmsService {

    void save(Sms sms);

    List<Sms> list();

    List<Sms> list(int pageNo,int pageCount);

    List<Sms> list(int pageNo,int pageCount,int code);
}
