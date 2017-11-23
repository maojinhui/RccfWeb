package com.rccf.controller.produce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.PageConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.produce.AProduceDiya;
import com.rccf.model.Employee;
import com.rccf.model.temp.ProduceTem;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.produce.DataUtil;
import com.rccf.util.produce.PageUtil;
import com.rccf.util.response.Page;
import com.rccf.util.verify.AgencyVerify;
import com.rccf.util.verify.ProduceVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.rccf.util.response.Page.limit;

@Controller
@RequestMapping(value = "/prod", produces = UrlConstants.PRODUCES)
public class ProduceController {


    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/listPage")
    public ModelAndView listPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/info/list")
    public String listAllProduce(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        //登录用户
        String depart = employee.getDepartment();
        int role = employee.getRole();

//        if(depart.contains("系统")){
//
//        }else if(depart.contains("市场")){
//
//        }
        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if(!Strings.isNullOrEmpty(pageNo)){
            p=Integer.valueOf(pageNo);
        }
        int offset = (p-1)* PageConstants.EVERYPAGE;
        String limitStr = " limit "+offset+","+PageConstants.EVERYPAGE;
        String sql = "SELECT *\n" +
                "FROM (SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         1 AS type,`state`,create_time\n" +
                "       FROM `a_produce_diya` as p\n" +
                "       UNION ALL\n" +
                "       SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         2 AS type,`state`,create_time\n" +
                "       FROM `a_produce_zhiya` as p\n" +
                "     ) AS data\n" +
                "ORDER BY data.create_time DESC "+limitStr;
        String sql_total = "SELECT count(*)\n" +
                "FROM (SELECT `id`"+
                "       FROM `a_produce_diya` as p\n" +
                "       UNION ALL\n" +
                "       SELECT `id`"+
                "       FROM `a_produce_zhiya` as p\n" +
                "     ) AS data ";


        String data = limit(baseService, sql_total, sql,ProduceTem.class);

        return data;
    }


    /**
     * 添加抵押产品页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/diyaInseret")
    public ModelAndView diyaAddPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_diya_add");
        PageUtil.addAgencys(modelAndView, baseService);
        return modelAndView;
    }


    /**
     * 抵押产品详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/diyaDetail")
    public ModelAndView diyaDetail(HttpServletRequest request) {
//        String produce_id= request.getParameter("produce_id");
//        if(Strings.isNullOrEmpty(produce_id)){
//            return ResponseUtil.pageFail("参数错误");
//        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_diya");
        PageUtil.addAgencys(modelAndView, baseService);
        return modelAndView;
    }

    /**
     * 抵押产品编辑
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit/diya")
    public String editDiyaProduce(HttpServletRequest request) {

        Employee employee = BackUtil.getLoginEmployee(request, employeeService);

        String oldProduceData = "";
        AProduceDiya produce = null;
        String produce_id = request.getParameter("produce_id");

        if (!Strings.isNullOrEmpty(produce_id)) {
            produce = (AProduceDiya) baseService.get(AProduceDiya.class, Integer.valueOf(produce_id));
            if (produce == null) {
                return ResponseUtil.fail(0, "没有找到该产品");
            }
            oldProduceData = produce.toString();
        } else {
            produce = new AProduceDiya();
            produce.setState(3);
            produce.setCreateTime(DateUtil.date2Timestamp(new Date()));
        }
        String produce_code = request.getParameter("produce_code");
        String agency_id = request.getParameter("agency_id");
        String agency_name = request.getParameter("agency_name");
        String produce_name = request.getParameter("produce_name");
        if (Strings.isNullOrEmpty(produce_code)) {
            return ResponseUtil.fail(0, "产品编号不能为空");
        }
        if (Strings.isNullOrEmpty(agency_id)) {
            return ResponseUtil.fail(0, "请填写正确的机构信息");
        } else {
            boolean hasagency = AgencyVerify.hasAgencyByid(baseService, Integer.valueOf(agency_id));
            if (!hasagency) {
                return ResponseUtil.fail(0, "请填写正确的机构信息.");
            }
        }
        produce.setCode(produce_code);
        boolean has = ProduceVerify.hasProduceCode(baseService, produce_code);
        if (has) {
            return ResponseUtil.fail(0, "产品编号已存在");
        }
        produce.setName(produce_name);
        produce.setAgencyId(Integer.valueOf(agency_id));
        produce.setAgencyName(agency_name);

        String produce_bid = request.getParameter("produce_bid");
        String produce_adapt_crown = request.getParameter("produce_adapt_crown");
        String repayment_type = request.getParameter("repayment_type");
        String loan_scale = request.getParameter("loan_scale");
        String loan_scale_other = request.getParameter("loan_scale_other");

        if (Strings.isNullOrEmpty(produce_bid)) {
            produce.setLoanBidType(null);
        } else {
            produce.setLoanBidType(Integer.valueOf(produce_bid));
        }
        produce.setLoanPeople(produce_adapt_crown);
        produce.setRepaymentType(repayment_type);
        produce.setLoanScale(loan_scale);
        produce.setLoanSacelOther(loan_scale_other);


        String produce_area = request.getParameter("produce_area");
        String produce_area_other = request.getParameter("produce_area_other");
        String produce_loan_amount_tao = request.getParameter("produce_loan_amount_tao");
        String produce_loan_amount = request.getParameter("produce_loan_amount");
        String produce_loan_amount_other = request.getParameter("produce_loan_amount_other");

        produce.setHouseArea(produce_area);
        produce.setHouseAreaOther(produce_area_other);
        produce.setLoanAmount(produce_loan_amount);
        produce.setLoanAmountTao(produce_loan_amount_tao);
        produce.setLoanAmountOther(produce_loan_amount_other);


        String loan_rate = request.getParameter("loan_rate");
        String loan_rate_other = request.getParameter("loan_rate_other");
        String person_material = request.getParameter("person_material");
        String company_material = request.getParameter("company_material");
        produce.setLoanRate(loan_rate);
        produce.setLoanRateOther(loan_rate_other);
        produce.setCompanyMaterial(company_material);
        produce.setPersonMaterial(person_material);

        String loan_min_age = request.getParameter("loan_min_age");
        String loan_max_age = request.getParameter("loan_max_age");
        String loan_age_other = request.getParameter("loan_age_other");
        String loan_min_month = request.getParameter("loan_min_month");
        String loan_max_month = request.getParameter("loan_max_month");
        String loan_month_other = request.getParameter("loan_month_other");

        if (Strings.isNullOrEmpty(loan_min_age)) {
            produce.setMinAge(null);
        } else {
            produce.setMinAge(Integer.valueOf(loan_min_age));
        }
        if (Strings.isNullOrEmpty(loan_max_age)) {
            produce.setMaxAge(null);
        } else {
            produce.setMaxAge(Integer.valueOf(loan_max_age));
        }
        produce.setAgeOther(loan_age_other);


        if (Strings.isNullOrEmpty(loan_min_month)) {
            produce.setMinMonth(null);
        } else {
            produce.setMinMonth(Integer.valueOf(loan_min_month));
        }
        if (Strings.isNullOrEmpty(loan_max_month)) {
            produce.setMixMonth(null);
        } else {
            produce.setMixMonth(Integer.valueOf(loan_max_month));
        }
        produce.setFixedMonth(loan_month_other);


        String apply_diya_type = request.getParameter("apply_diya_type");
        String house_ownership = request.getParameter("house_ownership");
        String apply_loan_type = request.getParameter("apply_loan_type");
        String apply_house_age = request.getParameter("apply_house_age");
        produce.setDiyaType(apply_diya_type);
        produce.setHouseOwnership(house_ownership);
        produce.setApplyLoanType(apply_loan_type);
        if (Strings.isNullOrEmpty(apply_house_age)) {
            produce.setApplyHouseAge(null);
        } else {
            produce.setApplyHouseAge(Integer.valueOf(apply_house_age));
        }

        String apply_house_nature = request.getParameter("apply_house_nature");
        String folk_diya_affect = request.getParameter("folk_diya_affect");
        String max_houseageplusloanyer = request.getParameter("max_houseageplusloanyer");
        String diffent_loan_mortgage = request.getParameter("diffent_loan_mortgage");
        String process_details = request.getParameter("process_details");

        produce.setApplyHouseNature(apply_house_nature);
//        produce.setFolkMortgageAffect(folk_diya_affect);
        if (Strings.isNullOrEmpty(folk_diya_affect)) {
            produce.setFolkMortgageAffect(null);
        } else {
            produce.setFolkMortgageAffect(Integer.valueOf(folk_diya_affect));
        }

        if (Strings.isNullOrEmpty(diffent_loan_mortgage)) {
            produce.setDifferentLoanMortgage(null);
        } else {
            produce.setDifferentLoanMortgage(Integer.valueOf(diffent_loan_mortgage));
        }

        if (Strings.isNullOrEmpty(max_houseageplusloanyer)) {
            produce.setLoanMaxHouseageplusloanyear(null);
        } else {
            produce.setLoanMaxHouseageplusloanyear(Integer.valueOf(max_houseageplusloanyer));
        }
        produce.setProcessDetails(process_details);


        String advantage = request.getParameter("advantage");
        String disadvantage = request.getParameter("disadvantage");
        String notice = request.getParameter("notice");
        String shoot_reason = request.getParameter("shoot_reason");
        produce.setAdvantage(advantage);
        produce.setDisadvantage(disadvantage);
        produce.setNotice(notice);
        produce.setShootReason(shoot_reason);


        boolean save = baseService.save(produce);

        if (save) {
            DataUtil.saveProduceNotify(baseService, oldProduceData, produce, employee.getId());
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail(0, "保存失败");
        }

    }


    /**
     * 添加质押产品页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/zhiyaInseret")
    public ModelAndView zhiyaAddPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_zhiya_add");
        PageUtil.addAgencys(modelAndView, baseService);
        return modelAndView;
    }


}
