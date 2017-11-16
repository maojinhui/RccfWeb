package com.rccf.controller;

import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/script")
public class ScriptController {

    @ResponseBody
    @RequestMapping
    public String getValue(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String name = request.getParameter("name");
        String value = request.getParameter("value");

        if (Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.success_jsonp(callback, "[]");
        }
    }

}
