package com.rccf.service.impl;

import com.rccf.dao.SmsDao;
import com.rccf.model.Sms;
import com.rccf.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by greatland on 17/7/14.
 */
@Service
public class SmsServiceImpl implements SmsService {


    @Autowired
    private SmsDao smsDao;

    public void save(Sms sms) {
        smsDao.save(sms);
    }

    public List<Sms> list() {
        return smsDao.list();
    }

    public List<Sms> list(int pageNo, int pageCount) {
        return smsDao.list(pageNo, pageCount);
    }

    public List<Sms> list(int pageNo, int pageCount, int code) {
        return smsDao.list(pageNo, pageCount, code);
    }
}
