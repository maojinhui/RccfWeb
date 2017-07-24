package com.rccf.dao;

import com.rccf.model.Subcribe;
import com.rccf.model.Unsubcribe;

import java.util.List;

/**
 * Created by greatland on 17/7/20.
 */
public interface SubcribeDao {

    void saveSubcribe(Subcribe subcribe);

    void saveUnsubcribe(Unsubcribe unsubcribe);


    List<Subcribe> listSubcriberByPage(int offset, int length);

    List<Unsubcribe> listUnsubcribeByPage(int offset, int length);

}
