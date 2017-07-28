package com.rccf.controller;

import com.rccf.constants.ResponseConstants;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.sms.ShumiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/comm",produces = {"text/html;charset=UTF-8;"})
public class CommonController {

    @RequestMapping(value = "/code")
    @ResponseBody
    public String getCode(HttpServletRequest request){
        String phone = request.getParameter("phone");
        if (null == phone){
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        if (Strings.isMobileNO(phone)){
            String result = ShumiUtil.sendSimple(phone);
            if (null == result){
                return ResponseUtil.fail(0,ResponseConstants.MSG_SEND_CODE_ERROR);
            }else{
                return  ResponseUtil.success();
            }
        }
        return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_FORMAT_ERROR);
    }
}
