package com.rccf.util.produce;

import com.rccf.model.produce.AProduceDiya;
import com.rccf.model.AProduceProcess;
import com.rccf.model.produce.AProduceZhiya;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;

import java.util.Date;

public class DataUtil {

    /**
     * 记录抵押产品修改记录
     *
     * @param baseService
     * @param old
     * @param now
     * @param eid
     */
    public static void saveProduceNotify(BaseService baseService, String old, AProduceDiya now, int eid) {
        AProduceProcess produceProcess = new AProduceProcess();
        produceProcess.setProduceType(1);
        produceProcess.setAdminPerson(eid);
        if (old != null) {
            produceProcess.setOldContent(old.toString());
        }
        if (now != null) {
            produceProcess.setProduceId(now.getId());
            produceProcess.setNewContent(now.toString());
        }

        produceProcess.setAdminTime(DateUtil.date2Timestamp(new Date()));
        baseService.save(produceProcess);
    }


    /**
     * 记录抵押产品修改记录
     *
     * @param baseService
     * @param old
     * @param now
     * @param eid
     */
    public static void saveProduceNotify(BaseService baseService, String old, AProduceZhiya now, int eid) {
        AProduceProcess produceProcess = new AProduceProcess();
        produceProcess.setAdminPerson(eid);
        produceProcess.setProduceType(2);
        if (old != null) {
            produceProcess.setOldContent(old.toString());
        }
        if (now != null) {
            produceProcess.setProduceId(now.getId());
            produceProcess.setNewContent(now.toString());
        }

        produceProcess.setAdminTime(DateUtil.date2Timestamp(new Date()));
        baseService.save(produceProcess);
    }


}
