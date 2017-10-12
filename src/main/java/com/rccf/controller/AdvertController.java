package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.PageConstants;
import com.rccf.constants.ResponseConstants;
import com.rccf.model.Loanapply;
import com.rccf.service.BaseService;
import com.rccf.service.LoanApplyService;
import com.rccf.util.*;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/advert", produces = {"text/html;charset=UTF-8;"})
public class AdvertController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private LoanApplyService loanApplyService;

    /**
     * 营销工具分享内容
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/weixin01")
    public ModelAndView advertPage(HttpServletRequest request) {
        String from = request.getParameter("from");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/weixin_advert01");
        if (null != from) {
            modelAndView.addObject("from", from);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/weixin02")
    public ModelAndView advertPage2(HttpServletRequest request) {
        String from = request.getParameter("from");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/weixin_advert02");
        if (null != from) {
            modelAndView.addObject("from", from);
        }
        return modelAndView;
    }


    /**
     * 广告内部申请
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apply")
    public String advertApply(HttpServletRequest request) {

        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String code = request.getParameter("code");

        String clerk_phone = request.getParameter("clerk_phone");
        String clerk_name = request.getParameter("clerk_name");
        String channel_name = request.getParameter("cheannel_name");
        String channel_phone = request.getParameter("channel_phone");


        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        if (Strings.isNullOrEmpty(code)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_NOT_NULL);
        }
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        String memCode = (String) spyMemcachedManager.get(phone);
        if (!code.equals(memCode)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_ERROE);
        }
        Loanapply loanapply = new Loanapply();
        loanapply.setCreateTime(DateUtil.date2Timestamp(new Date()));
        loanapply.setPhone(phone);
        loanapply.setStat(0);

        if (!Strings.isNullOrEmpty(name)) {
            loanapply.setRealName(name);
        }
        boolean state = loanApplyService.save(loanapply);

        if (state) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/apply_nocode")
    public String advertApplyNotCode(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");

        String clerk_phone = request.getParameter("clerk_phone");
        String clerk_name = request.getParameter("clerk_name");
        String channel_name = request.getParameter("cheannel_name");
        String channel_phone = request.getParameter("channel_phone");

        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Loanapply.class);
        detachedCriteria.add(Restrictions.eq("phone", phone));
        detachedCriteria.addOrder(Order.desc("createTime"));
        List list = baseService.getList(detachedCriteria);

        if (list != null && list.size() > 0) {
            Loanapply loan = (Loanapply) list.get(0);
            Date date_loan = DateUtil.timestamp2Date(loan.getCreateTime());
            Date date_now = new Date(System.currentTimeMillis());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date_loan);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date_now);

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                    .get(Calendar.YEAR);
            boolean isSameMonth = isSameYear
                    && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            boolean isSameDate = isSameMonth
                    && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                    .get(Calendar.DAY_OF_MONTH);

            if (isSameDate) {
                return ResponseUtil.fail(0, "您已经申请成功了，您还可以打电话联系我们，客服电话:4006-810-688。");
            }
        }
        Loanapply loanapply = new Loanapply();
        loanapply.setCreateTime(DateUtil.date2Timestamp(new Date()));
        loanapply.setPhone(phone);
        loanapply.setStat(0);
        if (!Strings.isNullOrEmpty(clerk_name)) {
            loanapply.setClerkName(clerk_name);
        }
        if (!Strings.isNullOrEmpty(clerk_phone)) {
            loanapply.setClerkPhone(clerk_phone);
        }

        if (!Strings.isNullOrEmpty(channel_name)) {
            loanapply.setChannelName(channel_name);
        }

        if (!Strings.isNullOrEmpty(channel_phone)) {
            loanapply.setChannelPhone(channel_phone);
        }

        if (!Strings.isNullOrEmpty(name)) {
            loanapply.setRealName(name);
        }
        boolean state = loanApplyService.save(loanapply);
        if (state) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail(0, "申请失败");
        }
    }


    /**
     * 公众平台申请记录
     *
     * @return
     */
    @RequestMapping(value = "/record")
    public ModelAndView smsRecord() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Loanapply.class);
        detachedCriteria.addOrder(Order.desc("createTime"));

        int count = baseService.getCount(detachedCriteria);
        int pages = count / PageConstants.EVERYPAGE + 1;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/smsrecord");
        modelAndView.addObject("pages", pages);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/applylist")
    public String applylist(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "0";
        }
        int p = 0;
        try {
            p = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Loanapply.class);
        detachedCriteria.addOrder(Order.asc("stat"));
        detachedCriteria.addOrder(Order.desc("createTime"));
        int count = baseService.getCount(detachedCriteria);
        int everyPage = PageConstants.EVERYPAGE;
        Page page = PageUtil.createPage(everyPage, count, p);
        List<Loanapply> list = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success_list(count, everyPage, list);
    }


    @ResponseBody
    @RequestMapping(value = "/notifyloanapply")
    public String dealState(HttpServletRequest request) {
        String stat = request.getParameter("stat");
        String id = request.getParameter("id");
        if (null == id) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PARAMTER_ERROR);
        }
        if (null == stat) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PARAMTER_ERROR);
        }
        int p = 0;
        int s = 10;
        try {
            p = Integer.valueOf(id);
            s = Integer.valueOf(stat);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
        Loanapply loanapply = loanApplyService.getLoanapplyByID(p);
        if (null == loanapply) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PARAMTER_ERROR);
        }
        if (10 != s && loanapply.getStat() == s) {
            return ResponseUtil.fail(0, "本条记录已被其他人处理！");
        }
        if (loanapply.getStat() == 0) {
            loanapply.setStat(1);
        } else {
            loanapply.setStat(0);
        }
        boolean save = loanApplyService.save(loanapply);
        if (save) {
            return ResponseUtil.success(loanapply);
        } else {
            return ResponseUtil.fail(0, "保存错误");
        }
    }


    @RequestMapping(value = "/mlist")
    public ModelAndView marketList() {
        return new ModelAndView("ad/marketlist");
    }


    @RequestMapping(value = "/weixin94index")
    public ModelAndView advert_9_4(HttpServletRequest request) {
        String clerk_phone = request.getParameter("clerk_phone");
        String clerk_name = request.getParameter("clerk_name");
        String channel_name = request.getParameter("cheannel_name");
        String channel_phone = request.getParameter("channel_phone");

        String from = request.getParameter("from");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/weixin_advert_9_4");
        if (null != from) {
            modelAndView.addObject("from", from);
        }
        String url = "http://weixin.rccfkg.com/advert/weixin94index";
        String share_url = "http://weixin.rccfkg.com/advert/weixin94index";

        if (Strings.isNullOrEmpty(clerk_name) && Strings.isNullOrEmpty(clerk_phone)
                && Strings.isNullOrEmpty(channel_name) && Strings.isNullOrEmpty(channel_phone)) {
        } else {
            share_url += "?";
            url += "?";
        }
        if (!Strings.isNullOrEmpty(clerk_name)) {
            modelAndView.addObject("clerk_name", clerk_name);
            share_url += "clerk_name=" + clerk_name + "&";
            url += "clerk_name=" + clerk_name + "&";
        }
        if (!Strings.isNullOrEmpty(clerk_phone)) {
            modelAndView.addObject("clerk_phone", clerk_phone);
            share_url += "clerk_phone=" + clerk_phone + "&";
            url += "clerk_phone=" + clerk_phone + "&";
        }
        if (!Strings.isNullOrEmpty(channel_name)) {
            modelAndView.addObject("channel_name", channel_name);
            share_url += "channel_name=" + channel_name + "&";
            url += "channel_name=" + channel_name + "&";
        }
        if (!Strings.isNullOrEmpty(channel_phone)) {
            modelAndView.addObject("channel_phone", channel_phone);
            share_url += "channel_phone=" + channel_phone + "&";
            url += "channel_phone=" + channel_phone + "&";
        }
        url = url.substring(0, url.length() - 1);
        share_url = share_url.substring(0, share_url.length() - 1);
        modelAndView.addObject("url", url);
        modelAndView.addObject("share_url", share_url);

        return modelAndView;
    }

    @RequestMapping(value = "/weixin94apply")
    public ModelAndView advert_9_4_apply(HttpServletRequest request) {
        String clerk_phone = request.getParameter("clerk_phone");
        String clerk_name = request.getParameter("clerk_name");
        String channel_name = request.getParameter("cheannel_name");
        String channel_phone = request.getParameter("channel_phone");

        String from = request.getParameter("from");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ad/weixin_advert_9_4_apply");
        if (null != from) {
            modelAndView.addObject("from", from);
        }
        String url = "http://weixin.rccfkg.com/advert/weixin94apply";
        String share_url = "http://weixin.rccfkg.com/advert/weixin94index";

        if (Strings.isNullOrEmpty(clerk_name) && Strings.isNullOrEmpty(clerk_phone)
                && Strings.isNullOrEmpty(channel_name) && Strings.isNullOrEmpty(channel_phone)) {
        } else {
            share_url += "?";
            url += "?";
        }
        if (!Strings.isNullOrEmpty(clerk_name)) {
            modelAndView.addObject("clerk_name", clerk_name);
            share_url += "clerk_name=" + clerk_name + "&";
            url += "clerk_name=" + clerk_name + "&";
        }
        if (!Strings.isNullOrEmpty(clerk_phone)) {
            modelAndView.addObject("clerk_phone", clerk_phone);
            share_url += "clerk_phone=" + clerk_phone + "&";
            url += "clerk_phone=" + clerk_phone + "&";
        }
        if (!Strings.isNullOrEmpty(channel_name)) {
            modelAndView.addObject("channel_name", channel_name);
            share_url += "channel_name=" + channel_name + "&";
            url += "channel_name=" + channel_name + "&";
        }
        if (!Strings.isNullOrEmpty(channel_phone)) {
            modelAndView.addObject("channel_phone", channel_phone);
            share_url += "channel_phone=" + channel_phone + "&";
            url += "channel_phone=" + channel_phone + "&";
        }
        url = url.substring(0, url.length() - 1);
        share_url = share_url.substring(0, share_url.length() - 1);
        modelAndView.addObject("url", url);
        modelAndView.addObject("share_url", share_url);
        return modelAndView;
    }


    /**
     * 给分享添加标识
     *
     * @return
     */
    @RequestMapping(value = "/addsign")
    public ModelAndView advert_addsign() {
        return new ModelAndView("/ad/addsign");
    }


}
