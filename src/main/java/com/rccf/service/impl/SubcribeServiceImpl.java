package com.rccf.service.impl;

import com.rccf.dao.SubcribeDao;
import com.rccf.model.Subcribe;
import com.rccf.model.Unsubcribe;
import com.rccf.service.SubcribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by greatland on 17/7/21.
 */
@Service
public class SubcribeServiceImpl implements SubcribeService {

    @Autowired
    private SubcribeDao subcribeDao;

    public void saveSubcribe(Subcribe subcribe) {
        subcribeDao.saveSubcribe(subcribe);
    }

    public void saveUnsubcribe(Unsubcribe unsubcribe) {
        subcribeDao.saveUnsubcribe(unsubcribe);
    }
}
