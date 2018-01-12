package com.rccf.util.produce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.model.Employee;
import com.rccf.model.ProducePersonMaterial;
import com.rccf.model.produce.*;
import com.rccf.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class DZProducePage {


    BaseService baseService;

    public DZProducePage(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * 添加创建人名字
     *
     * @param modelAndView
     * @param baseProduct
     */
    public   void addCreatePerson(ModelAndView modelAndView, BaseProduct baseProduct) {
        Integer createPerson = baseProduct.getCreatePerson();
        Employee employee = (Employee) baseService.get(Employee.class, createPerson);
        if (employee != null) {
            modelAndView.addObject("createPName", employee.getName());
        }
    }

    public   void addLoanAmountTao(ModelAndView modelAndView, BaseProduct baseProduct) {
        String loanAmountTao = baseProduct.getLoanAmountTao();
        if (loanAmountTao == null || loanAmountTao.equals("[]")) {
            modelAndView.addObject("amountTao", "单套多套可做未知");
        }
        String tao = "";
        if (loanAmountTao.contains("1")) {
            if (loanAmountTao.contains("2")) {
                tao = "单套多套都可做";
            } else {
                tao = "单套可做多套不可做";
            }
        } else {
            if (loanAmountTao.contains("2")) {
                tao = "单套不可做多套可做";
            } else {
                tao = "";
            }
        }
        modelAndView.addObject("amountTao", tao);
    }

    public   void addProduceRepayment(ModelAndView modelAndView, BaseProduct produce) {
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

    public   void addProduceArea(ModelAndView modelAndView, BaseProduct produce) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProduceArea.class);
        List<ProduceArea> areas = baseService.getList(criteria);
        String area = produce.getHouseArea();
        String thing = "";
        if (area != null) {
            JSONArray array = JSON.parseArray(area);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < areas.size(); j++) {
                    ProduceArea area1 = areas.get(j);
                    if (area1.getAreaId() == type) {
                        thing += area1.getAreaName() + "、";
                    }
                }
            }
            if (thing.length() > 1) {
                thing = thing.substring(0, thing.length() - 1);
            }
            if (produce.getHouseAreaOther() != null) {
                thing += ",补充区域：" + produce.getHouseAreaOther();
            }
            modelAndView.addObject("produceArea", thing);
        }
    }

    public   void addHouseNature(ModelAndView modelAndView, BaseProduct produce) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProduceHouseNature.class);
        List<ProduceHouseNature> natures = baseService.getList(criteria);
        String nature = produce.getApplyHouseNature();
        String thing = "";
        if (nature != null) {
            JSONArray array = JSON.parseArray(nature);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < natures.size(); j++) {
                    ProduceHouseNature nature1 = natures.get(j);
                    if (nature1.getId() == type) {
                        thing += nature1.getName() + "、";
                    }
                }
            }
            if (thing.length() > 1) {
                thing = thing.substring(0, thing.length() - 1);
            }
            modelAndView.addObject("produceNature", thing);
        }


    }

    public   void addPersonMaterial(ModelAndView modelAndView, BaseProduct produce) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProducePersonMaterial.class);
        List<ProducePersonMaterial> personMaterials = baseService.getList(criteria);
        String personMaterial = produce.getPersonMaterial();
        String thing = "";
        if (personMaterial != null) {
            JSONArray array = JSON.parseArray(personMaterial);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < personMaterials.size(); j++) {
                    ProducePersonMaterial personMaterial1 = personMaterials.get(j);
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

    public   void addCompanyMaterial(ModelAndView modelAndView, BaseProduct produce) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ProduceCompanyMaterial.class);
        List<ProduceCompanyMaterial> companyMaterials = baseService.getList(criteria);
        String companyMaterial = produce.getCompanyMaterial();
        String thing = "";
        if (companyMaterial != null) {
            JSONArray array = JSON.parseArray(companyMaterial);
            for (int i = 0; i < array.size(); i++) {
                int type = array.getIntValue(i);
                for (int j = 0; j < companyMaterials.size(); j++) {
                    ProduceCompanyMaterial personMaterial1 = companyMaterials.get(j);
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

    public  void addRpayment(ModelAndView modelAndView, BaseProduct produce ){
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
