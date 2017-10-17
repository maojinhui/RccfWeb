package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/appother", produces = UrlConstants.PRODUCES)
public class AppOtherController {

    @RequestMapping(value = "/versionPage")
    public ModelAndView versionPage() {
        return new ModelAndView("/front/other/version");
    }


}
