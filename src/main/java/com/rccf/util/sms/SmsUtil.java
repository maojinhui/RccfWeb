package com.rccf.util.sms;

import com.alibaba.fastjson.JSON;
import com.rccf.component.SpyMemcachedManager;
import com.rccf.model.Sms;
import com.rccf.model.SmsResult;
import com.rccf.service.SmsService;
import com.rccf.util.DateUtil;
import com.rccf.util.SpringContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by greatland on 17/7/13.
 */

public class SmsUtil {


    Logger logger = Logger.getLogger(SmsUtil.class);


    /**
     * 保存短信记录
     */
    public static void saveSms(String data) {
        SmsResult result = JSON.parseObject(data, SmsResult.class);

    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    public static boolean sendCode(String mobile) {
        try {
            SmsService smsService = (SmsService) SpringContextUtil.getBean("smsServiceImpl");
            SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");

            Random random = new Random();
            int res = random.nextInt(9000) + 1000;
            String code = String.valueOf(res);
            String data = JavaSmsApi.sendSms(code, mobile);
            SmsResult result = JSON.parseObject(data, SmsResult.class);
            //保存短信发送记录

            smsService.save(getSms(result, mobile, code));
            if (result.getCode() == 0) {//发送成功
                //将验证码保存至Memcached

                spyMemcachedManager.set(mobile, code, 10 * 60);//过期时间设置为十分钟
                return true;
            } else {//发送失败

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取Sms
     *
     * @param result
     * @param code
     * @return
     */
    private static Sms getSms(SmsResult result, String phone, String code) {
        Sms sms = new Sms();
        sms.setCode(code);
        sms.setPhone(phone);
        sms.setResultCode(result.getCode());
        sms.setResultMsg(result.getMsg());
        sms.setResultSid(String.valueOf(result.getSid()));
        sms.setSendtime(DateUtil.date2Timestamp(new Date()));
        return sms;

    }


}
