package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.UrlConstants;
import com.rccf.model.IndexRecommend;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/index", produces = UrlConstants.PRODUCES)
public class AppIndexController {

    @Autowired
    BaseService baseService;


    @ResponseBody
    @RequestMapping(value = "/save")
    public String saveRecommend() {
        IndexRecommend indexRecommend = new IndexRecommend();
        indexRecommend.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        indexRecommend.setId(1);
        indexRecommend.setStat(0);
        boolean b = baseService.save(indexRecommend);
        return ResponseUtil.success(b);
    }

    @ResponseBody
    @RequestMapping(value = "/recommendList")
    public String recommendList(HttpServletRequest request) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(IndexRecommend.class);
        detachedCriteria.add(Restrictions.eq("stat", 1));
        detachedCriteria.addOrder(Order.desc("recommend"));
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(15, count, 0);
        List<IndexRecommend> list = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(list);
    }

}
