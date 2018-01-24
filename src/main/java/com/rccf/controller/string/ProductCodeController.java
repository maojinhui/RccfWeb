package com.rccf.controller.string;

import com.rccf.constants.UrlConstants;
import com.rccf.model.produce.AProduceCredit;
import com.rccf.model.produce.AProduceDiya;
import com.rccf.model.produce.AProduceZhiya;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.chars.Pinyin;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/util/char" ,produces = UrlConstants.PRODUCES)
public class ProductCodeController {


    @Autowired
    BaseService baseService;

    @Resource
    private PlatformTransactionManager txManager;
//    private Map<String , Integer> agencysc = new HashMap<>();



    @ResponseBody
    @RequestMapping(value = "/code/produce/diya")
    public String generateDiyaCode(){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

        Map<String ,Integer > agencyid = new HashMap<>();
        Map<String , Integer> agencysc = new HashMap<>();
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceDiya.class);
        criteria.addOrder(Order.asc("createTime"));
        try {
            List<AProduceDiya> diyas = baseService.getList(criteria);
            int k=0;
            for (int i = 0 ; i< diyas.size();i++) {
                String str = "1.";
                AProduceDiya diya = diyas.get(i);
                String agency_name = diya.getAgencyName();
                String product_name = diya.getName();
                String pPinyin = "";
                if (Strings.isNullOrEmpty(product_name)) {

                } else {
                    pPinyin = "-"+Pinyin.getPinYinHeadChar(product_name);
                }
                if(agency_name.trim().equals(product_name.trim())){
                    pPinyin ="";
                }

                String aPinyin = Pinyin.getPinYinHeadChar(agency_name);

                if (agencysc.containsKey(agency_name)) {
                    int c = agencysc.get(agency_name);
                    c++;
                    agencysc.put(agency_name, c);
                    int id = agencyid.get(agency_name);
                    str = str + id + "." + c + "-DY" + "-" + aPinyin +  pPinyin;
                } else {
                    int id = ++k;
                    agencysc.put(agency_name, 1);
                    str = str +  id + ".1-DY" + "-" + aPinyin +  pPinyin;
                    agencyid.put(agency_name,k);
                }
                diya.setCode(str);
                baseService.save(diya);

            }
            txManager.commit(status);
        }catch(Exception e){
            txManager.rollback(status);
        }



        return ResponseUtil.success();

//        return ResponseUtil.fail(0,"产品编号生成失败");
    }


    @ResponseBody
    @RequestMapping(value = "/code/produce/zhiya")
    public String generateZhiyaCode(){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

        Map<String ,Integer > agencyid = new HashMap<>();
        Map<String , Integer> agencysc = new HashMap<>();
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceZhiya.class);
        criteria.addOrder(Order.asc("createTime"));
        try {
            List<AProduceZhiya> diyas = baseService.getList(criteria);
            int k=0;
            for (int i = 0 ; i< diyas.size();i++) {
                String str = "3.";
                AProduceZhiya diya = diyas.get(i);
                String agency_name = diya.getAgencyName();
                String product_name = diya.getName();
                String pPinyin = "";
                if (Strings.isNullOrEmpty(product_name)) {

                } else {
                    pPinyin = "-"+Pinyin.getPinYinHeadChar(product_name);
                }
                if(agency_name.trim().equals(product_name.trim())){
                    pPinyin ="";
                }

                String aPinyin = Pinyin.getPinYinHeadChar(agency_name);

                if (agencysc.containsKey(agency_name)) {
                    int c = agencysc.get(agency_name);
                    c++;
                    agencysc.put(agency_name, c);
                    int id = agencyid.get(agency_name);
                    str = str + id + "." + c + "-ZY" + "-" + aPinyin +  pPinyin;
                } else {
                    int id = ++k;
                    agencysc.put(agency_name, 1);
                    str = str +  id + ".1-ZY" + "-" + aPinyin +  pPinyin;
                    agencyid.put(agency_name,k);
                }
                diya.setCode(str);
                baseService.save(diya);

            }
            txManager.commit(status);
        }catch(Exception e){
            txManager.rollback(status);
        }



        return ResponseUtil.success();

//        return ResponseUtil.fail(0,"产品编号生成失败");
    }




    @ResponseBody
    @RequestMapping(value = "/code/produce/xindai")
    public String generateXindaiCode(){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(defaultTransactionDefinition);

        Map<String ,Integer > agencyid = new HashMap<>();
        Map<String , Integer> agencysc = new HashMap<>();
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceCredit.class);
        criteria.addOrder(Order.asc("createTime"));
        try {
            List<AProduceCredit> diyas = baseService.getList(criteria);
            int k=0;
            for (int i = 0 ; i< diyas.size();i++) {
                String str = "2.";
                AProduceCredit diya = diyas.get(i);
                String agency_name = diya.getAgencyName();
                String product_name = diya.getName();
                String pPinyin = "";
                if (Strings.isNullOrEmpty(product_name)) {

                } else {
                    pPinyin = "-"+Pinyin.getPinYinHeadChar(product_name);
                }
                if(agency_name.trim().equals(product_name.trim())){
                    pPinyin ="";
                }

                String aPinyin = Pinyin.getPinYinHeadChar(agency_name);

                if (agencysc.containsKey(agency_name)) {
                    int c = agencysc.get(agency_name);
                    c++;
                    agencysc.put(agency_name, c);
                    int id = agencyid.get(agency_name);
                    str = str + id + "." + c + "-XY" + "-" + aPinyin +  pPinyin;
                } else {
                    int id = ++k;
                    agencysc.put(agency_name, 1);
                    str = str +  id + ".1-XY" + "-" + aPinyin +  pPinyin;
                    agencyid.put(agency_name,k);
                }
                diya.setCode(str);
                baseService.save(diya);

            }
            txManager.commit(status);
        }catch(Exception e){
            txManager.rollback(status);
        }



        return ResponseUtil.success();

//        return ResponseUtil.fail(0,"产品编号生成失败");
    }


}
