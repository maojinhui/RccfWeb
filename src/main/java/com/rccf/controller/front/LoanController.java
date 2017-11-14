package com.rccf.controller.front;

import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.model.UserApply;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.weixin.WeixinUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/loan", produces = UrlConstants.PRODUCES)
public class LoanController {
    @Autowired
    BaseService baseService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/default")
    public ModelAndView loanDefault(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/main_products");
        return modelAndView;
    }

    @RequestMapping(value = "/diya")
    public ModelAndView loanDiya(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_diya");
        modelAndView.addObject("phone", phone);

        return modelAndView;
    }


    @RequestMapping(value = "/baodan")
    public ModelAndView loanBaodan(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_baodan");
        modelAndView.addObject("phone", phone);

        return modelAndView;
    }


    @RequestMapping(value = "/xinshui")
    public ModelAndView loanXinshui(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_xinshui");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/shengyi")
    public ModelAndView loanshengyi(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_shengyi");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/chedai")
    public ModelAndView loanchedai(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_chedai");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @RequestMapping(value = "/jingyingdai")
    public ModelAndView loanjingyingdai(HttpServletRequest request) {
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        String phone = user.getPhone();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/loan_jingyingdai");
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/applyloan")
    public String applyLoan(HttpServletRequest request){
        String type = request.getParameter("type");
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        if (user == null) {
            return ResponseUtil.fail(0, "申请失败");
        }
        String phone = user.getPhone();
        if (phone == null) {
            return ResponseUtil.fail(0, "用户还未绑定手机号");
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(UserApply.class);
        criteria.add(Restrictions.eq("type", type));
        criteria.add(Restrictions.eq("userId", user.getUserId()));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            return ResponseUtil.fail(0, "已申请过同类型的贷款，您可以拨打我们的官方客服电话:4006-810-688 联系我们");
        }
        UserApply apply = new UserApply();
        if (!Strings.isNullOrEmpty(type)) {
            apply.setType(type);
        }
        apply.setUserId(user.getUserId());
        apply.setPhone(phone);
        apply.setApplyTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        apply.setState(0);
        boolean save = baseService.save(apply);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "申请失败，请稍后重试");
    }

    @RequestMapping(value = "/success")
    public ModelAndView applySuccess(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/loan/apply_success");
        return modelAndView;
    }


}
