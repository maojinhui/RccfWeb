package com.rccf.controller.produce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.PageConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.ProducePersonMaterial;
import com.rccf.model.produce.*;
import com.rccf.model.Employee;
import com.rccf.model.temp.ProduceTem;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.produce.CreditProducePage;
import com.rccf.util.produce.DataUtil;
import com.rccf.util.produce.PageUtil;
import com.rccf.util.verify.ProduceVerify;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    private Logger logger = Logger.getLogger(this.getClass());


    @Resource
    private PlatformTransactionManager transactionManager;


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

        boolean showAll = false;
        if (depart.contains("系统")) {
            showAll = true;
        } else if (depart.contains("市场部")) {
            if (role <= 2) {
                showAll = true;
            } else {
                showAll = false;
            }
        } else {
            return ResponseUtil.fail(0, "暂无查看产品权限");
        }

        String eInfo = "";
        if (!showAll) {
            eInfo = "where p.create_person = " + employee.getId();
        }
//        if(depart.contains("系统")){
//
//        }else if(depart.contains("市场")){
//
//        }
        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if (!Strings.isNullOrEmpty(pageNo)) {
            p = Integer.valueOf(pageNo);
        }
        int offset = (p - 1) * PageConstants.EVERYPAGE;
        String limitStr = " limit " + offset + "," + PageConstants.EVERYPAGE;
        String sql = "SELECT *\n" +
                "FROM (SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         1 AS type,`state`,create_time,log,\n" +
                "(SELECT audit_opinion from a_produce_audit_log WHERE type=1 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_diya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "       SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         2 AS type,`state`,create_time,log,\n" +
                "       (SELECT audit_opinion from a_produce_audit_log WHERE type=2 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_zhiya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "      SELECT `id`, `name`, `code`, agency_id,\n" +
                "       (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "       0 AS type,`state`,create_time,log,\n" +
                "       (SELECT audit_opinion from a_produce_audit_log WHERE type=0 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason      " +
                "       FROM `a_produce_credit` as p\n" + eInfo +
                "     ) AS data\n" +
                "ORDER BY data.create_time DESC " + limitStr;
        String sql_total = "SELECT count(*)\n" +
                "FROM (SELECT `id`" +
                "       FROM `a_produce_diya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "       SELECT `id`" +
                "       FROM `a_produce_zhiya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "      SELECT `id`       FROM `a_produce_credit` as p \n" + eInfo +
                "     ) AS data ";


        String data = limit(baseService, sql_total, sql, ProduceTem.class);

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
        String produce_id = request.getParameter("produce_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_diya_add");
        PageUtil.addAgencys(modelAndView, baseService);
        AProduceDiya diya = null;
        if (!Strings.isNullOrEmpty(produce_id)) {
            diya = (AProduceDiya) baseService.get(AProduceDiya.class, Integer.valueOf(produce_id));
            modelAndView.addObject("produce", diya);
        }
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
        String produce_id = request.getParameter("produce_id");
        if (Strings.isNullOrEmpty(produce_id)) {
            return ResponseUtil.pageFail("参数错误");
        }
        int id = Integer.valueOf(produce_id);
        AProduceDiya produce = (AProduceDiya) baseService.get(AProduceDiya.class, id);
        if (produce == null) {
            return ResponseUtil.pageFail("没有找到该产品");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_diya_details");
        modelAndView.addObject("produce", produce);
        PageUtil.addAgencys(modelAndView, baseService);
        addCreatePerson(modelAndView, produce);
        addLoanAmountTao(modelAndView, produce);
        addProduceRepayment(modelAndView, produce);
        addProduceArea(modelAndView, produce);
        addHouseNature(modelAndView, produce);
        addPersonMaterial(modelAndView, produce);
        addCompanyMaterial(modelAndView, produce);

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
            produce.setCreateTime(DateUtil.date2Timestamp(new Date()));
            produce.setCreatePerson(employee.getId());
        }

        produce.setState(3);

        String produce_code = request.getParameter("produce_code");
//        String agency_id = request.getParameter("agency_id");
        String agency_name = request.getParameter("agency_name");
//        RAgency ragencyByName = AgencyVerify.getRagencyByName(baseService, agency_name);
//        if (ragencyByName == null) {
//            return ResponseUtil.fail(0, "请填写正确的机构名称");
//        }
//        int agency_id = ragencyByName.getId();

        if (Strings.isNullOrEmpty(agency_name)) {
            return ResponseUtil.fail(0, "请填写正确的机构名称");
        }
        String produce_name = request.getParameter("produce_name");

//        if (Strings.isNullOrEmpty(produce_code)) {
//            return ResponseUtil.fail(0, "产品编号不能为空");
//        }
//        boolean has = ProduceVerify.hasProduceCode(baseService, produce_code);
//        if (has) {
//            return ResponseUtil.fail(0, "产品编号已存在");
//        }
        produce.setCode(produce_code);
        produce.setName(produce_name);
//        produce.setAgencyId(agency_id);
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

        String recommend = request.getParameter("recommend");
        String entry_time = request.getParameter("entry_time");
        if (Strings.isNullOrEmpty(entry_time)) {
            return ResponseUtil.fail(0, "准入时间不能为空");
        }
        produce.setRecommend(recommend);
        produce.setEntryTime(DateUtil.strToSqlDate(entry_time));

        try {
            boolean save = baseService.save(produce);
            if (save) {
                AProduceAuditLog log = new AProduceAuditLog();
                log.setProduceId(produce.getId());
                log.setSubmit(employee.getId());
                log.setProduceType(1);
                log.setSubmitTime(DateUtil.date2Timestamp(new Date()));
                boolean savelog = baseService.save(log);
                if (!savelog) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试");
                }
                produce.setLog(log.getId());
                boolean savelog2 = baseService.save(produce);
                if (!savelog2) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试");
                }
                DataUtil.saveProduceNotify(baseService, oldProduceData, produce, employee.getId());
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "保存失败");
            }
        } catch (org.hibernate.AssertionFailure assertionFailure) {
            return ResponseUtil.fail(0, "产品编号已存在");
        } catch (Exception e) {
            e.printStackTrace();
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
        String produce_id = request.getParameter("produce_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_zhiya_add");
        PageUtil.addAgencys(modelAndView, baseService);
        AProduceZhiya produce = null;
        if (!Strings.isNullOrEmpty(produce_id)) {
            produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, Integer.valueOf(produce_id));
            modelAndView.addObject("produce", produce);
        }
        return modelAndView;
    }

    /**
     * 质押产品编辑
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit/zhiya")
    public String editZhiyaProduce(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);

        String oldProduceData = "";
        AProduceZhiya produce = null;
        String produce_id = request.getParameter("produce_id");

        if (!Strings.isNullOrEmpty(produce_id)) {
            produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, Integer.valueOf(produce_id));
            if (produce == null) {
                return ResponseUtil.fail(0, "没有找到该产品");
            }
            oldProduceData = produce.toString();
        } else {
            produce = new AProduceZhiya();
            produce.setCreateTime(DateUtil.date2Timestamp(new Date()));
            produce.setCreatePerson(employee.getId());
        }

        produce.setState(3);

        String produce_code = request.getParameter("produce_code");
//        String agency_id = request.getParameter("agency_id");
        String agency_name = request.getParameter("agency_name");
//        RAgency ragencyByName = AgencyVerify.getRagencyByName(baseService, agency_name);
//        if (ragencyByName == null) {
//            return ResponseUtil.fail(0, "请填写正确的机构名称");
//        }
//        int agency_id = ragencyByName.getId();

        if (Strings.isNullOrEmpty(agency_name)) {
            return ResponseUtil.fail(0, "请填写正确的机构名称");
        }
        String produce_name = request.getParameter("produce_name");

//        if (Strings.isNullOrEmpty(produce_code)) {
//            return ResponseUtil.fail(0, "产品编号不能为空");
//        }
//        boolean has = ProduceVerify.hasProduceCode(baseService, produce_code);
//        if (has) {
//            return ResponseUtil.fail(0, "产品编号已存在");
//        }
        produce.setCode(produce_code);
        produce.setName(produce_name);
//        produce.setAgencyId(agency_id);
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


//        String loan_rate = request.getParameter("loan_rate");
//        String loan_rate_other = request.getParameter("loan_rate_other");
        String person_material = request.getParameter("person_material");
        String company_material = request.getParameter("company_material");


        String supportExtension = request.getParameter("support_extension");
        String extensionFee = request.getParameter("extension_fee");
        String loan_rate = request.getParameter("loan_rate");

        if (!Strings.isNullOrEmpty(supportExtension)) {
            produce.setSupportExtension(Integer.valueOf(supportExtension));
        }
        produce.setExtensionFee(extensionFee);
        produce.setLoanRate(loan_rate);
//        produce.setLoanRate(loan_rate);
//        produce.setLoanRateOther(loan_rate_other);
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

        String recommend = request.getParameter("recommend");
        String entry_time = request.getParameter("entry_time");
        if (Strings.isNullOrEmpty(entry_time)) {
            return ResponseUtil.fail(0, "准入时间不能为空");
        }
        produce.setRecommend(recommend);
        produce.setEntryTime(DateUtil.strToSqlDate(entry_time));


        try {
            boolean save = baseService.save(produce);
            if (save) {
                AProduceAuditLog log = new AProduceAuditLog();
                log.setProduceId(produce.getId());
                log.setSubmit(employee.getId());
                log.setProduceType(2);
                log.setSubmitTime(DateUtil.date2Timestamp(new Date()));
                boolean savelog = baseService.save(log);
                if (!savelog) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试1");
                }
                produce.setLog(log.getId());
                boolean savelog2 = baseService.save(produce);
                if (!savelog2) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试2");
                }


                DataUtil.saveProduceNotify(baseService, oldProduceData, produce, employee.getId());
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "保存失败");
            }
        } catch (org.hibernate.AssertionFailure assertionFailure) {
            return ResponseUtil.fail(0, "产品编号已存在");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail(0, "保存失败");
        }
    }

    /**
     * 抵押产品详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/zhiyaDetail")
    public ModelAndView zhiyaDetail(HttpServletRequest request) {
        String produce_id = request.getParameter("produce_id");
        if (Strings.isNullOrEmpty(produce_id)) {
            return ResponseUtil.pageFail("参数错误");
        }
        int id = Integer.valueOf(produce_id);
        AProduceZhiya produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, id);
        if (produce == null) {
            return ResponseUtil.pageFail("没有找到该产品");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_zhiya_details");
        modelAndView.addObject("produce", produce);
        PageUtil.addAgencys(modelAndView, baseService);
        addCreatePerson(modelAndView, produce);
        addLoanAmountTao(modelAndView, produce);
        addProduceRepayment(modelAndView, produce);
        addProduceArea(modelAndView, produce);
        addHouseNature(modelAndView, produce);
        addPersonMaterial(modelAndView, produce);
        addCompanyMaterial(modelAndView, produce);

        return modelAndView;
    }


    @RequestMapping(value = "/credit/insert")
    public ModelAndView creditAddPage(HttpServletRequest request) {
        String produce_id = request.getParameter("produce_id");
        AProduceCredit produce = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_xindai_add");
        if (!Strings.isNullOrEmpty(produce_id)) {
            produce = (AProduceCredit) baseService.get(AProduceCredit.class, Integer.valueOf(produce_id));
            modelAndView.addObject("produce", produce);
        }
        PageUtil.addAgencys(modelAndView, baseService);
        PageUtil.addCreditType(modelAndView, baseService);
        PageUtil.addCreditMaterialPerson(modelAndView, baseService);
        PageUtil.addCreditMaterialCompany(modelAndView, baseService);
        return modelAndView;
    }

    @RequestMapping(value = "/creditDetail")
    public ModelAndView creditDetail(HttpServletRequest request) {
        String produce_id = request.getParameter("produce_id");
        if (Strings.isNullOrEmpty(produce_id)) {
            return ResponseUtil.pageFail("参数错误");
        }
        int id = Integer.valueOf(produce_id);
        AProduceCredit produce = (AProduceCredit) baseService.get(AProduceCredit.class, id);
        if (produce == null) {
            return ResponseUtil.pageFail("没有找到该产品");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_xinddai_details");
        modelAndView.addObject("produce", produce);
        CreditProducePage.addCreatePerson(modelAndView, produce, baseService);
        CreditProducePage.addPersonMaterial(modelAndView, produce, baseService);
        CreditProducePage.addCompanyMaterial(modelAndView, produce, baseService);
        CreditProducePage.addRpayment(modelAndView, produce, baseService);


        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/edit/credit")
    public String editCreditProduce(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String oldProduceData = "";
        AProduceCredit produce = null;
        String produce_id = request.getParameter("produce_id");

        if (!Strings.isNullOrEmpty(produce_id)) {
            produce = (AProduceCredit) baseService.get(AProduceCredit.class, Integer.valueOf(produce_id));
            if (produce == null) {
                return ResponseUtil.fail(0, "没有找到该产品");
            }
            oldProduceData = produce.toString();
        } else {
            produce = new AProduceCredit();
            produce.setCreateTime(DateUtil.date2Timestamp(new Date()));
            produce.setCreatePerson(employee.getId());
        }
        produce.setState(3);


        String produce_code = request.getParameter("code");


        String agency_name = request.getParameter("agency_name");
//        RAgency ragencyByName = AgencyVerify.getRagencyByName(baseService, agency_name);
//        if (ragencyByName == null) {
//            return ResponseUtil.fail(0, "请填写正确的机构名称");
//        }
//        int agency_id = ragencyByName.getId();
        String produce_name = request.getParameter("name");
        if (Strings.isNullOrEmpty(agency_name)) {
            return ResponseUtil.fail(0, "请填写正确的机构名称");
        }

//        if (Strings.isNullOrEmpty(produce_code)) {
//            return ResponseUtil.fail(0, "产品编号不能为空");
//        }
        produce.setName(produce_name);
        produce.setCode(produce_code);
        produce.setAgencyName(agency_name);
//        produce.setAgencyId(agency_id);

        String credit_type = request.getParameter("credit_type");
        String loan_people = request.getParameter("loan_people");
        String repayment = request.getParameter("repayment");
        produce.setLoanCreditType(Integer.valueOf(credit_type));
        produce.setLoanPeople(loan_people);
        produce.setRepaymentType(repayment);


        String loan_time_min = request.getParameter("loan_time_min");
        String loan_time_max = request.getParameter("loan_time_max");
        String loan_amount_min = request.getParameter("loan_amount_min");
        String loan_amount_max = request.getParameter("loan_amount_max");
        String loan_rate_min = request.getParameter("loan_rate_min");
        String loan_rate_max = request.getParameter("loan_rate_max");
        String loan_term_min = request.getParameter("loan_term_min");
        String loan_term_max = request.getParameter("loan_term_max");
        produce.setLoanTimeMin(getInteger(loan_time_min));
        produce.setLoanTimeMax(getInteger(loan_time_max));

        produce.setLoanAmountMin(getInteger(loan_amount_min));
        produce.setLoanAmountMax(getInteger(loan_amount_max));

        produce.setLoanRateMin(getDouble(loan_rate_min));
        produce.setLoanRateMax(getDouble(loan_rate_max));

        produce.setLoanTermMin(getInteger(loan_term_min));
        produce.setLoanTermMax(getInteger(loan_term_max));

        String loan_shangkou = request.getParameter("loan_shagnkou");
        String loan_pingtaifei = request.getParameter("loan_pingtaifei");
        String weiyuejin = request.getParameter("weiyuejin");
        String person_material = request.getParameter("person_material");
        String company_material = request.getParameter("company_material");
        produce.setLoanShagnkouDescription(loan_shangkou);
        produce.setLoanPingtaifeiDescription(loan_pingtaifei);
        produce.setLoanWeiyuejinDescription(weiyuejin);
        produce.setLoanMaterialPersonal(person_material);
        produce.setLoanMaterialCompany(company_material);

        String credit_require_check = request.getParameter("credit_require_check");
        String credit_require_overdue = request.getParameter("credit_require_overdue");
        String credit_require_owe = request.getParameter("credit_require_owe");
        String credit_require_other = request.getParameter("credit_require_other");

        String produce_process = request.getParameter("produce_process");
        String produce_advantage = request.getParameter("produce_advantage");
        String proudce_disadvantage = request.getParameter("produce_disadvantage");
        String notice = request.getParameter("notice");
        String produce_shootreason = request.getParameter("produce_shootreason");
        produce.setCreditInquireClaim(credit_require_check);
        produce.setCreditOverdueClaim(credit_require_overdue);
        produce.setCreditDebtClaim(credit_require_owe);
        produce.setCreditOtherClaim(credit_require_other);
        produce.setProcessDetail(produce_process);
        produce.setAdvantage(produce_advantage);
        produce.setDisadvantage(proudce_disadvantage);
        produce.setNotice(notice);
        produce.setShootReason(produce_shootreason);

        String recommend = request.getParameter("recommend");
        String entry_time = request.getParameter("entry_time");
        if (Strings.isNullOrEmpty(entry_time)) {
            return ResponseUtil.fail(0, "准入时间不能为空");
        }
        produce.setRecommend(recommend);
        produce.setEntryTime(DateUtil.strToSqlDate(entry_time));


        String access = request.getParameter("access");
        produce.setLoanAccess(access);


        try {
            boolean save = baseService.save(produce);
            if (save) {
                AProduceAuditLog log = new AProduceAuditLog();
                log.setProduceId(produce.getId());
                log.setSubmit(employee.getId());
                log.setProduceType(0);
                log.setSubmitTime(DateUtil.date2Timestamp(new Date()));
                boolean savelog = baseService.save(log);
                if (!savelog) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试1");
                }
                produce.setLog(log.getId());
                boolean savelog2 = baseService.save(produce);
                if (!savelog2) {
                    return ResponseUtil.fail(0, "提交日志失败，请重试2");
                }
                DataUtil.saveProduceNotify(baseService, oldProduceData, produce, employee.getId());
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "保存失败");
            }
        } catch (org.hibernate.AssertionFailure assertionFailure) {
            return ResponseUtil.fail(0, "产品编号已存在");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail(0, "保存失败");
        }

    }


    @RequestMapping(value = "/audit/list")
    public ModelAndView audioList(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_product_audit_list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/audit/all/auditlist")
    public String listAllAuditProduce(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        //登录用户
        String depart = employee.getDepartment();
        int role = employee.getRole();

        boolean showAll = false;
        if (depart.contains("系统")) {
            showAll = true;
        } else if (depart.contains("市场部")) {
            if (role <= 2) {
                showAll = true;
            } else {
                showAll = false;
            }
        } else {
            return ResponseUtil.fail(0, "暂无查看产品权限");
        }

//        if(depart.contains("系统")){
//
//        }else if(depart.contains("市场")){
//
//        }

        String eInfo = "";
        if (!showAll) {
            eInfo = "where p.create_person = " + employee.getId();
        }

        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if (!Strings.isNullOrEmpty(pageNo)) {
            p = Integer.valueOf(pageNo);
        }
        int offset = (p - 1) * PageConstants.EVERYPAGE;
        String limitStr = " limit " + offset + "," + PageConstants.EVERYPAGE;
        String sql = "SELECT *\n" +
                "FROM (SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         1 AS type,`state`,create_time,log,\n" +
                "(SELECT audit_opinion from a_produce_audit_log WHERE type=1 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_diya` as p \n" + eInfo +
                "       UNION ALL\n" +
                "       SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         2 AS type,`state`,create_time,log,\n" +
                "(SELECT audit_opinion from a_produce_audit_log WHERE type=2 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_zhiya` as p\n" + eInfo +
                "       UNION ALL\n" +
                "       SELECT `id`, `name`, `code`, agency_id,\n" +
                "         (SELECT name from r_agency ra WHERE ra.id = p.agency_id) as agency_name,\n" +
                "         0 AS type,`state`,create_time,log,\n" +
                "(SELECT audit_opinion from a_produce_audit_log WHERE type=0 and produce_id=p.id ORDER BY audit_time DESC  LIMIT  1) as reason" +
                "       FROM `a_produce_credit` as p\n" + eInfo +
                "     ) AS data\n" +
                "     WHERE state=3 \n" +
                "ORDER BY data.create_time DESC " + limitStr;
        String sql_total = "SELECT count(*)\n" +
                "FROM (SELECT id,state       FROM `a_produce_diya` as p\n" + eInfo +
                "      UNION ALL\n" +
                "      SELECT id,state       FROM `a_produce_zhiya` as p\n" + eInfo +
                "      UNION ALL\n" +
                "      SELECT id,state       FROM `a_produce_credit` as p\n" + eInfo +
                "     ) AS data\n" +
                "WHERE state=3";


        String data = limit(baseService, sql_total, sql, ProduceTem.class);

        return data;
    }

    @RequestMapping(value = "/audit/page")
    public ModelAndView auditDetailPage(HttpServletRequest request) {
        String log_id = request.getParameter("log_id");
        String type = request.getParameter("type");
        String produce_id = request.getParameter("produce_id");
        BaseProduct produce = null;
        int pid = Integer.valueOf(produce_id);
        int type_ = Integer.valueOf(type);
        ModelAndView modelAndView = new ModelAndView();
        switch (type_) {
            case 1:
                modelAndView.setViewName("/back/product/p_product_diya_audit");
                produce = (AProduceDiya) baseService.get(AProduceDiya.class, pid);
                modelAndView.addObject("produce", produce);
                modelAndView.addObject("log_id", log_id);
                PageUtil.addAgencys(modelAndView, baseService);
                addCreatePerson(modelAndView, produce);
                addLoanAmountTao(modelAndView, produce);
                addProduceRepayment(modelAndView, produce);
                addProduceArea(modelAndView, produce);
                addHouseNature(modelAndView, produce);
                addPersonMaterial(modelAndView, produce);
                addCompanyMaterial(modelAndView, produce);
                break;
            case 2:
                modelAndView.setViewName("/back/product/p_product_zhiya_audit");
                produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, pid);
                modelAndView.addObject("produce", produce);
                modelAndView.addObject("log_id", log_id);
                PageUtil.addAgencys(modelAndView, baseService);
                addCreatePerson(modelAndView, produce);
                addLoanAmountTao(modelAndView, produce);
                addProduceRepayment(modelAndView, produce);
                addProduceArea(modelAndView, produce);
                addHouseNature(modelAndView, produce);
                addPersonMaterial(modelAndView, produce);
                addCompanyMaterial(modelAndView, produce);
                break;
            case 0:
                modelAndView.setViewName("/back/product/p_product_xindadi_audit");
                AProduceCredit produce_credit = (AProduceCredit) baseService.get(AProduceCredit.class, pid);
                modelAndView.addObject("produce", produce_credit);
                modelAndView.addObject("log_id", log_id);
                CreditProducePage.addCreatePerson(modelAndView, produce_credit, baseService);
                CreditProducePage.addPersonMaterial(modelAndView, produce_credit, baseService);
                CreditProducePage.addCompanyMaterial(modelAndView, produce_credit, baseService);
                CreditProducePage.addRpayment(modelAndView, produce_credit, baseService);
                break;
            default:
                break;

        }

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/audit/submit")
    public String auditState(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String log_id = request.getParameter("log_id");
        String produce_id = request.getParameter("produce_id");
        String type = request.getParameter("type");
        String opinion = request.getParameter("opinon");
        String state = request.getParameter("state");
        int pid = Integer.valueOf(produce_id);
        int stat = Integer.valueOf(state);
        int typ = Integer.valueOf(type);
//        BaseProduct produce = null;

        boolean has = ProduceVerify.hasDiyaProduceByID(baseService, produce_id, type);
        if (!has) {
            return ResponseUtil.fail(0, "没有找到该产品");
        }
        if (Strings.isNullOrEmpty(log_id)) {
            return ResponseUtil.fail(0, "没有找到提交记录");
        }
        boolean save = false;
        switch (typ) {
            case 1:
                AProduceDiya produce_diya = (AProduceDiya) baseService.get(AProduceDiya.class, pid);
                if (stat == 1) {
                    produce_diya.setState(1);
                } else {
                    produce_diya.setState(2);
                }
                save = baseService.save(produce_diya);
                if (!save) {
                    return ResponseUtil.fail(0, "产品更新失败");
                }
                break;
            case 2:
                AProduceZhiya produce_zhiya = (AProduceZhiya) baseService.get(AProduceZhiya.class, pid);
                if (stat == 1) {
                    produce_zhiya.setState(1);
                } else {
                    produce_zhiya.setState(2);
                }
                save = baseService.save(produce_zhiya);
                if (!save) {
                    return ResponseUtil.fail(0, "产品更新失败");
                }
                break;
            case 0:
                AProduceCredit produce_credit = (AProduceCredit) baseService.get(AProduceCredit.class, pid);
                if (stat == 1) {
                    produce_credit.setState(1);
                } else {
                    produce_credit.setState(2);
                }
                save = baseService.save(produce_credit);
                if (!save) {
                    return ResponseUtil.fail(0, "产品更新失败");
                }
                break;

            default:
                break;
        }

        AProduceAuditLog log = (AProduceAuditLog) baseService.get(AProduceAuditLog.class, Integer.valueOf(log_id));
        log.setAudit(employee.getId());
        log.setAuditOpinion(opinion);
        log.setAuditTime(DateUtil.date2Timestamp(new Date()));
        log.setProduceId(Integer.valueOf(produce_id));
        log.setState(stat);
        boolean savelog = baseService.save(log);
        if (savelog) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail();
    }

    @ResponseBody
    @RequestMapping(value = "/filter/list")
    public String findProduce(HttpServletRequest request) {
        String loan_type = request.getParameter("loan_type");
        String loan_amount = request.getParameter("loan_amount");
        String loan_term = request.getParameter("loan_term");
        String loan_repayment_type = request.getParameter("loan_repayment_type");
        String loan_people = request.getParameter("loan_people");
        String loan_credit_type = request.getParameter("loan_credit_type");
        String where = "  where 1  ";
        String limit = "  limit 5  ";
        String sql_allproduce = "select * from (\n" +
                "  (SELECT id,0 as type , agency_name, code,name, repayment_type , loan_people ,  loan_amount_min,loan_amount_max,loan_term_min,loan_term_max,loan_rate_min,loan_rate_max , loan_credit_type from a_produce_credit where state = 1\n" +
                "   UNION ALL\n" +
                "   SELECT id,1 as type ,agency_name, code,name, repayment_type  , loan_people , 10 as loan_amount_min, getJsonArrayMaxAmount(loan_amount) as loan_amount_max , min_month as loan_term_min , mix_month as loan_term_max , getJsonArrayMaxRate(loan_rate) as loan_rate_max , getJsonArrayMinRate(loan_rate) as loan_rate_min , -1 as loan_credit_type from a_produce_diya where state = 1\n" +
                "   UNION ALL\n" +
                "   SELECT id,2 as type , agency_name, code,name, repayment_type , loan_people , 10 as loan_amount_min, getJsonArrayMaxAmount(loan_amount) as loan_amount_max , min_month as loan_term_min , mix_month as loan_term_max , getJsonArrayMaxRate(loan_rate) as loan_rate_max , getJsonArrayMinRate(loan_rate) as loan_rate_min , -1 as loan_credit_type  from a_produce_zhiya where state = 1\n" +
                "  ) as p\n" +
                ")  ";
        if (!Strings.isNullOrEmpty(loan_amount)) {
            where += "  and p.loan_amount_min<= " + Integer.valueOf(loan_amount) + " and  p.loan_amount_max>=" + loan_amount + "\n ";
        }
        if (!Strings.isNullOrEmpty(loan_term)) {
            where += "  and p.loan_term_min<= " + Integer.valueOf(loan_term) + " and  p.loan_term_max>=" + loan_term + "\n ";
        }
        if (!Strings.isNullOrEmpty(loan_repayment_type)) {
            where += "  and  find_in_set(" + loan_repayment_type + " , substring(repayment_type,2,char_length(repayment_type)-2) ) ";
        }

        if (!Strings.isNullOrEmpty(loan_people)) {
            JSONArray array = JSON.parseArray(loan_people);
            if (array.size() > 0) {
                String str = "";
                for (int i = 0; i < array.size(); i++) {
                    String people = array.get(i).toString();
                    if (i == 0) {
                        str += "  and  ( find_in_set(" + people + " , substring(loan_people,2,char_length(loan_people)-2) )  ";
                    } else {
                        str += " or find_in_set(" + people + " , substring(loan_people,2,char_length(loan_people)-2) ) ";
                    }
                    if (i == array.size() - 1) {
                        str += " )";
                    }
                }
                where += str;
            }
        }

        if (!Strings.isNullOrEmpty(loan_type)) {
            Integer type = Integer.valueOf(loan_type);
            if (type == 0) {//信用贷
                where += " and p.type = 0 ";
            } else if (type == 1) {//抵质押
                where += " and ( p.type = 1 || p.type = 2) ";
            }
        }
        if (!Strings.isNullOrEmpty(loan_credit_type) && !loan_credit_type.equals("[]")) {
            if(Strings.isNullOrEmpty(loan_type) || loan_type.equals("0")){
                where += " and p.type = 0 ";
                JSONArray array = JSON.parseArray(loan_credit_type);
                String str = "";
                for (int i = 0; i < array.size(); i++) {
                    Integer type = array.getIntValue(i);
                    if (i == 0) {
                        str += " and ( " + getCreditTypeStr(type);
                    }else{
                        str +=  "  or  " + getCreditTypeStr(type);
                    }
                    if(i==array.size()-1){
                        str+=" ) ";
                    }
                }
                where += str;
            }
        }

        String sql_result = sql_allproduce + where + limit;
        List<ProduceFront> list = baseService.queryBySqlFormatClass(ProduceFront.class, sql_result);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        if (list != null && list.size() > 0) {
            return ResponseUtil.success_front(array);
        } else {
            return ResponseUtil.success_front(null);
        }
    }


    /**
     * 添加创建人名字
     *
     * @param modelAndView
     * @param baseProduct
     */
    private void addCreatePerson(ModelAndView modelAndView, BaseProduct baseProduct) {
        Integer createPerson = baseProduct.getCreatePerson();
        Employee employee = (Employee) baseService.get(Employee.class, createPerson);
        if (employee != null) {
            modelAndView.addObject("createPName", employee.getName());
        }
    }


    private void addLoanAmountTao(ModelAndView modelAndView, BaseProduct baseProduct) {
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

    private void addProduceRepayment(ModelAndView modelAndView, BaseProduct produce) {
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

    private void addProduceArea(ModelAndView modelAndView, BaseProduct produce) {
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

    private void addHouseNature(ModelAndView modelAndView, BaseProduct produce) {
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

    private void addPersonMaterial(ModelAndView modelAndView, BaseProduct produce) {
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

    private void addCompanyMaterial(ModelAndView modelAndView, BaseProduct produce) {
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


    private Double getDouble(String value) {
        try {
            Double dou = Double.valueOf(value);
            return dou;
        } catch (Exception e) {
            return null;
        }
    }


    private Integer getInteger(String value) {
        try {
            Integer integer = Integer.valueOf(value);
            return integer;
        } catch (Exception e) {
            return null;
        }
    }


    public String getCreditTypeStr(Integer type){
        switch (type){
            case 1 : return " p.loan_credit_type = 1 ";
            case 2 : return " p.loan_credit_type = 4 or p.loan_credit_type = 5 or p.loan_credit_type = 6 or p.loan_credit_type = 7 or p.loan_credit_type=8 or  p.loan_credit_type=15 ";
            case 3 : return " p.loan_credit_type = 2 or p.loan_credit_type = 7 or p.loan_credit_type = 8 or p.loan_credit_type = 14 or p.loan_credit_type = 15   ";
            case 4 : return " p.loan_credit_type = 3 or p.loan_credit_type = 15  or p.loan_credit_type = 18 ";
            case 5 : return " p.loan_credit_type = 9 or p.loan_credit_type = 13 or p.loan_credit_type = 13 or p.loan_credit_type = 18 ";
            case 6 : return " p.loan_credit_type = 10 or p.loan_credit_type = 11 or p.loan_credit_type = 12 or p.loan_credit_type = 15 or p.loan_credit_type = 16 ";
            default: return "";
        }
    }

    public static void main(String[] args) {
        Integer integer = Integer.getInteger("1");
        System.out.println(integer);

    }





}