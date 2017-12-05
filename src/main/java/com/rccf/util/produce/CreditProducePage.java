package com.rccf.util.produce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.model.Employee;
import com.rccf.model.ProducePersonMaterial;
import com.rccf.model.produce.AProduceCredit;
import com.rccf.model.produce.AProduceCreditMaterialCompany;
import com.rccf.model.produce.AProduceCreditMaterialPerson;
import com.rccf.model.produce.ProduceRepayment;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class CreditProducePage {


    public static  void addCreatePerson(ModelAndView modelAndView, AProduceCredit produce , BaseService baseService) {
        Integer createPerson = produce.getCreatePerson();
        Employee employee = (Employee) baseService.get(Employee.class, createPerson);
        if (employee != null) {
            modelAndView.addObject("createPName", employee.getName());
        }
    }

    public static  void addPersonMaterial(ModelAndView modelAndView, AProduceCredit produce , BaseService baseService) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceCreditMaterialPerson.class);
        List<AProduceCreditMaterialPerson> personMaterials = baseService.getList(criteria);
        String personMaterial = produce.getLoanMaterialPersonal();
        String thing = "";
        if (personMaterial != null) {
            JSONArray array = JSON.parseArray(personMaterial);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < personMaterials.size(); j++) {
                    AProduceCreditMaterialPerson personMaterial1 = personMaterials.get(j);
                    if (personMaterial1.getId() == type) {
                        thing += personMaterial1.getName() + "、";
                    }
                }
            }
            if (thing.length() > 1) {
                thing = thing.substring(0, thing.length() - 1);
            }
            modelAndView.addObject("producePersonMaterial", thing);
        }
    }



    public static  void addCompanyMaterial(ModelAndView modelAndView, AProduceCredit produce , BaseService baseService) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AProduceCreditMaterialCompany.class);
        List<AProduceCreditMaterialCompany> personMaterials = baseService.getList(criteria);
        String personMaterial = produce.getLoanMaterialPersonal();
        String thing = "";
        if (personMaterial != null) {
            JSONArray array = JSON.parseArray(personMaterial);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < personMaterials.size(); j++) {
                    AProduceCreditMaterialCompany personMaterial1 = personMaterials.get(j);
                    if (personMaterial1.getId() == type) {
                        thing += personMaterial1.getName() + "、";
                    }
                }
            }
            if (thing.length() > 1) {
                thing = thing.substring(0, thing.length() - 1);
            }
            modelAndView.addObject("produceCompanyMaterial", thing);
        }
    }


    public static void addRpayment(ModelAndView modelAndView,AProduceCredit produce , BaseService baseService){
        DetachedCriteria criteria = DetachedCriteria.forClass(ProduceRepayment.class);
        List<ProduceRepayment> repayments = baseService.getList(criteria);
        String repayment = produce.getRepaymentType();
        String thing = "";
        if (repayment != null) {
            JSONArray array = JSON.parseArray(repayment);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < repayments.size(); j++) {
                    ProduceRepayment repayment1 = repayments.get(j);
                    if (repayment1.getId() == type) {
                        thing += repayment1.getName() + "、";
                    }
                }
            }
            if (thing.length() > 1) {
                thing = thing.substring(0, thing.length() - 1);
            }
            modelAndView.addObject("repaymentType", thing);
        }
    }


}
