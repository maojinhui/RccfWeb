package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.UrlConstants;
import com.rccf.model.ProductDiya;
import com.rccf.service.BaseService;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/util", produces = UrlConstants.PRODUCES)
public class UtilController {


    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/dyMatchPage")
    public ModelAndView dyMatchPage() {
        return new ModelAndView("util/dyMatch");
    }


    @ResponseBody
    @RequestMapping(value = "/dyMatch")
    public String dyMatch(HttpServletRequest request) {

        String use_type = request.getParameter("use_type");
        String amount_money = request.getParameter("amount_money");
        String user_age = request.getParameter("user_age");
        String loan_year = request.getParameter("loan_year");
        String loan_number = request.getParameter("loan_number");
        String house_area = request.getParameter("house_area");
        String house_nature = request.getParameter("house_nature");
        String house_age = request.getParameter("house_age");
        String house_company = request.getParameter("house_company");
        String repayment_type = request.getParameter("repayment_type");
        String folk_affect = request.getParameter("folk_affect");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProductDiya.class);
        detachedCriteria.addOrder(Order.desc("recommend"));
        if (!Strings.isNullOrEmpty(use_type)) {//用户贷款用途1:个人消费；2：企业经营
            if (use_type.equals("1")) {
                detachedCriteria.add(Restrictions.eq("personDo", 1));
                if (!Strings.isNullOrEmpty(amount_money)) {
                    detachedCriteria.add(Restrictions.ge("personMoney", Integer.valueOf(amount_money)));
                }
            } else if (use_type.equals("2")) {
                detachedCriteria.add(Restrictions.eq("companyDo", 1));
                if (!Strings.isNullOrEmpty(amount_money)) {
                    detachedCriteria.add(Restrictions.ge("companyMoney", Integer.valueOf(amount_money)));
                }
            }
        }
        if (!Strings.isNullOrEmpty(user_age)) {
            int age = Integer.valueOf(user_age);
            detachedCriteria.add(Restrictions.and(
                    Restrictions.le("minAge", age),
                    Restrictions.ge("maxAge", age)));
        }
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(30, count, 0);
        List list = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(list);

    }


}
