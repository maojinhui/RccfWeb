package com.rccf.controller.poster;


import com.rccf.constants.UrlConstants;
import com.rccf.model.poster.BPoster;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.image.MarkUtil;
import com.rccf.util.image.Watermark;
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
@RequestMapping(value = "/poster", produces = UrlConstants.PRODUCES)
public class PosterIndexController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/poster/index");
        DetachedCriteria criteria = DetachedCriteria.forClass(BPoster.class);
        List<BPoster> posters =  baseService.getList(criteria);
        modelAndView.addObject("posters",posters);
        return modelAndView;
    }

    @RequestMapping(value = "/show_v1")
    public ModelAndView showPoster(HttpServletRequest request) {
        String poster_id = request.getParameter("poster_id");
        BPoster poster = null;
        try {
            int id = Integer.valueOf(poster_id);
            poster = (BPoster) baseService.get(BPoster.class, id);
        } catch (Exception e) {
            return ResponseUtil.pageFail("没有找到海报");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/poster/poster_show_v1");
        modelAndView.addObject("bposter", poster);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/generate/img/default")
    public String generateImage(HttpServletRequest request) {
        String realPath = request.getRealPath("");
        String poster_id = request.getParameter("poster_id");
        BPoster poster = null;
        try {
            int id = Integer.valueOf(poster_id);
            poster = (BPoster) baseService.get(BPoster.class, id);
        } catch (Exception e) {
            return ResponseUtil.fail(0, "没有找到海报");
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(name)) {
            name = "钱多多";
        }
        if (Strings.isNullOrEmpty(phone)) {
            phone = "1888888888";
        }

        String backgroundImg = realPath + poster.getBackimg();
        String src = "/temp/t_" + new Date().getTime() + ".jpg";
        String targetPath = realPath + src;
        if (poster_id.equals("1")) {
            MarkUtil.firstImage(backgroundImg, targetPath, name, phone);
        } else if (poster_id.equals("2")) {
            MarkUtil.secondImage(backgroundImg, targetPath, name, phone);
        }
        return ResponseUtil.success(src);
    }


    @ResponseBody
    @RequestMapping(value = "/generate/img/v2")
    public String generateImageBynameAndPhone(HttpServletRequest request) {
        String realPath = request.getRealPath("");
        String poster_id = request.getParameter("poster_id");
        BPoster poster = null;
        try {
            int id = Integer.valueOf(poster_id);
            poster = (BPoster) baseService.get(BPoster.class, id);
        } catch (Exception e) {
            return ResponseUtil.fail(0, "没有找到海报");
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(name)) {
            name = "钱小融";
        }
        if (Strings.isNullOrEmpty(phone)) {
            phone = "1888888888";
        }
        String src = "/temp/t_" + new Date().getTime() + ".jpg";
        String targetPath = realPath+src;
        targetPath = Watermark.getWatermarkImage(poster, name , phone ,  targetPath);

        return ResponseUtil.success(src);
    }







    @RequestMapping(value = "/show_v2")
    public ModelAndView showV2(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/");
        return modelAndView;
    }






}
