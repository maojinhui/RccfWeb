package com.rccf.controller;


import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/app", produces = UrlConstants.PRODUCES)
public class AppController {


    @RequestMapping(value = "/homepage")
    public String homePage() {
        return "front/home";
    }

    @RequestMapping(value = "/mypage")
    public String myinfoPage(){
        return "front/myinfo";
    }
    @RequestMapping(value = "/producepage")
    public String producePage(){
        return "front/produce";
    }


    @RequestMapping(value = "/progresspage")
    public String progressPage(){
        return "front/progress";
    }

}
