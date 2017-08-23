package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.UrlConstants;
import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;
import com.rccf.service.BaseService;
import com.rccf.util.CookiesUtil;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/util", produces = UrlConstants.PRODUCES)
public class UtilController {


    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/dyMatchPage")
    public ModelAndView dyMatchPage(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "dyMatchPage", response);
        return new ModelAndView("util/dyMatch");
    }


    @RequestMapping(value = "/index")
    public ModelAndView indexPage(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "index", response);
        return new ModelAndView("util/index");
    }

    @RequestMapping(value = "/material_dyp")
    public ModelAndView personDyMaterial(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "material_dyp", response);
        return new ModelAndView("util/dyp_material");
    }

    @RequestMapping(value = "/material_dyc")
    public ModelAndView companyDyMaterial(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "material_dyc", response);
        return new ModelAndView("util/dyc_material");
    }

    @RequestMapping(value = "/material_xyd")
    public ModelAndView xindaiMaterial(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "material_xyd", response);
        return new ModelAndView("util/xyd_material");
    }

    @RequestMapping(value = "/xyd_rate")
    public ModelAndView xindaiRate(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "xyd_rate", response);
        return new ModelAndView("util/xyd_rate");
    }

    @RequestMapping(value = "/zyMatchPage")
    public ModelAndView zyMatchPage(HttpServletResponse response) {
        CookiesUtil.addCookies("type", "zyd_match", response);
        return new ModelAndView("util/zyMatch");
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
                if (!Strings.isNullOrEmpty(amount_money)) {//个人金额
                    detachedCriteria.add(Restrictions.ge("personMoney", Integer.valueOf(amount_money)));
                }
                if (!Strings.isNullOrEmpty(loan_number)) {//个人放款成数
                    detachedCriteria.add(Restrictions.ge("personNumber", Double.valueOf(loan_number)));
                }

            } else if (use_type.equals("2")) {
                detachedCriteria.add(Restrictions.eq("companyDo", 1));
                if (!Strings.isNullOrEmpty(amount_money)) {//企业金额
                    detachedCriteria.add(Restrictions.ge("companyMoney", Integer.valueOf(amount_money)));
                }
                if (!Strings.isNullOrEmpty(loan_number)) {//企业放款成数
                    detachedCriteria.add(Restrictions.or(//普通企业或者优良企业成数
                            Restrictions.ge("companyNumber", Double.valueOf(loan_number)),
                            Restrictions.ge("greatCompanyNumber", Double.valueOf(loan_number))));
                }
            }
        } else {
            //默认选择企业经营
            if (!Strings.isNullOrEmpty(amount_money)) {
                detachedCriteria.add(Restrictions.ge("companyMoney", Integer.valueOf(amount_money)));
            }
            if (!Strings.isNullOrEmpty(loan_number)) {//企业放款成数
                detachedCriteria.add(Restrictions.or(//普通企业或者优良企业成数
                        Restrictions.ge("companyNumber", Double.valueOf(loan_number)),
                        Restrictions.ge("greatCompanyNumber", Double.valueOf(loan_number))));
            }
        }
        //年龄限制
        if (!Strings.isNullOrEmpty(user_age)) {
            int age = Integer.valueOf(user_age);
            detachedCriteria.add(Restrictions.and(
                    Restrictions.le("minAge", age),
                    Restrictions.ge("maxAge", age)));
        }
        //贷款年限
        if (!Strings.isNullOrEmpty(loan_year)) {
            int year = Integer.valueOf(loan_year);
            detachedCriteria.add(Restrictions.and(
                    Restrictions.le("minLoanYear", year),
                    Restrictions.ge("maxLoanYear", year)));
        }
        //房屋区域
        if (!Strings.isNullOrEmpty(house_area)) {
            detachedCriteria.add(
                    Restrictions.or(Restrictions.like("houseArea", "%" + house_area + ",%"),
                            Restrictions.like("houseArea", "%" + house_area + "]%")));
        }
        //房屋性质
        if (!Strings.isNullOrEmpty(house_nature)) {
            detachedCriteria.add(
                    Restrictions.or(Restrictions.like("houseNature", "%" + house_nature + ",%"),
                            Restrictions.like("houseNature", "%" + house_nature + "]%")));
        }
        //房龄
        if (!Strings.isNullOrEmpty(house_age)) {
            int year = Integer.valueOf(house_age);
            detachedCriteria.add(Restrictions.le("houseYear", year));
        }
        //公司名下房产是否可做
        if (!Strings.isNullOrEmpty(house_company)) {
            if (house_company.equals("1")) {//选择了是公司名下
                detachedCriteria.add(Restrictions.eq("houseCompanyDo", 1));
            }
        }
        if (!Strings.isNullOrEmpty(repayment_type)) {
            detachedCriteria.add(Restrictions.or(
                    Restrictions.like("repaymentType", "%," + repayment_type + ",%"),
                    Restrictions.like("repaymentType", "%," + repayment_type + "]%"),
                    Restrictions.like("repaymentType", "%[" + repayment_type + ",%"),
                    Restrictions.like("repaymentType", "%[" + repayment_type + "]%")
            ));
        }
        if (!Strings.isNullOrEmpty(folk_affect)) {
            int affect = Integer.valueOf(folk_affect);
            detachedCriteria.add(Restrictions.eq("folkAffect", affect));
        }
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(30, count, 0);
        List list = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(list);

    }


    @ResponseBody
    @RequestMapping(value = "/zyMatch")
    public String zyMatch(HttpServletRequest request) {
        String user_age = request.getParameter("user_age");
        String amount_money = request.getParameter("amount_money");
        String hope_number = request.getParameter("hope_number");
        String area = request.getParameter("area");
        String repayment = request.getParameter("repayment");
        String house_nature = request.getParameter("house_nature");
        String house_age = request.getParameter("house_age");
        String erdi_can = request.getParameter("erdi_can");
        String can_extension = request.getParameter("canExtension");

        DetachedCriteria criteria = DetachedCriteria.forClass(ProductZhiya.class);
        if (!Strings.isNullOrEmpty(user_age)) {
            criteria.add(
                    Restrictions.and(
                            Restrictions.le("minAge", user_age),//比最小的大
                            Restrictions.ge("maxAge", user_age)));//比最大的小
        }

        if (!Strings.isNullOrEmpty(amount_money)) {
            criteria.add(Restrictions.or(
                    Restrictions.le("amountMoneyOne", amount_money),
                    Restrictions.le("amountMoneyMore", amount_money)
            ));
        }

        if (!Strings.isNullOrEmpty(hope_number)) {
            criteria.add(Restrictions.ge("moneyNumber", hope_number));
        }

        if (!Strings.isNullOrEmpty(area)) {
            criteria.add(
                    Restrictions.or(
                            Restrictions.like("houseArea", "%," + area + ",%"),
                            Restrictions.like("houseArea", "%," + area + "]%"),
                            Restrictions.like("houseArea", "%[" + area + ",%"),
                            Restrictions.like("houseArea", "%[" + area + "]%"))
            );
        }

        if (!Strings.isNullOrEmpty(repayment)) {
            criteria.add(Restrictions.or(
                    Restrictions.like("repayment", "%" + repayment + ",%"),
                    Restrictions.like("repayment", "%" + repayment + "]%")));
        }

        if (!Strings.isNullOrEmpty(house_nature)) {
            criteria.add(Restrictions.or(
                    Restrictions.like("houseNature", "%," + house_nature + ",%"),
                    Restrictions.like("houseNature", "%," + house_nature + "]%"),
                    Restrictions.like("houseNature", "%[" + house_nature + ",%"),
                    Restrictions.like("houseNature", "%[" + house_nature + "]%")
            ));
        }

        if (!Strings.isNullOrEmpty(house_age)) {
            criteria.add(Restrictions.ge("houseAge", house_age));
        }

        if (!Strings.isNullOrEmpty(can_extension)) {
            int can = Integer.valueOf(can_extension) - 1;
            criteria.add(Restrictions.eq("extension", can));
        }

        if (!Strings.isNullOrEmpty(erdi_can)) {
            int can = Integer.valueOf(erdi_can) - 1;
            criteria.add(Restrictions.eq("erdiDo", can));
        }
        int count = baseService.getCount(criteria);
        Page page = PageUtil.createPage(30, count, 0);
        List list = baseService.getList(page, criteria);
        return ResponseUtil.success(list);
    }


    /**
     * 根据cookie判断选中标签
     *
     * @param viewName
     * @param chooseType
     * @return
     */
    private ModelAndView getView(String viewName, String chooseType) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("type", chooseType);
        return modelAndView;
    }


}
