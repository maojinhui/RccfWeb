package com.rccf.util.sms;

import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.AccountConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Sms;
import com.rccf.service.SmsService;
import com.rccf.util.DateUtil;
import com.rccf.util.SpringContextUtil;
import com.rccf.util.encrypt.Base64;
import com.rccf.util.encrypt.Md5Encrypt;
import com.rccf.util.network.HttpUtil;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShumiUtil {

    Logger logger = Logger.getLogger(ShumiUtil.class);


    /**
     * 发送普通验证码短信
     *
     * @param mobile
     * @return
     */
    public static String sendSimple(String mobile) {
        SmsService smsService = (SmsService) SpringContextUtil.getBean("smsServiceImpl");
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        DateFormat format = new SimpleDateFormat("yyyymmddhhmmss");
        String timespan = format.format(new Date());
        String pwd = Md5Encrypt.getMD5(AccountConstants.SHUMI_PWD + timespan).toUpperCase();
        Random random = new Random();
        int res = random.nextInt(9000) + 1000;
        String code = String.valueOf(res);
        String _content = "【融成官网】" + code + "融成金服验证码，有效时间10分钟，请尽快验证";
        String content = Base64.getBase64(_content);
        Map<String, String> param = new HashMap<String, String>();
        param.put("userid", AccountConstants.SHUMI_ID);
        param.put("timespan", timespan);
        param.put("pwd", pwd);
        param.put("mobile", mobile);
        param.put("msgfmt", "UTF8");
        param.put("content", content);
        String result = HttpUtil.post(UrlConstants.URL_SHUMI_SEND_SIMPLE, param);
        if (!result.contains("-")) {
            spyMemcachedManager.set(mobile, code, 10 * 60);
            Sms sms = getSms(mobile, code, result, 1);
            smsService.save(sms);
        } else {
            Sms sms = getSms(mobile, code, result, Integer.valueOf(result));
            smsService.save(sms);
            return null;
        }
        return result;
    }

    /**
     * 查询余额
     *
     * @return
     */
    public static String getBalance() {
        Map<String, String> param = new HashMap<String, String>();
        DateFormat format = new SimpleDateFormat("yyyymmddhhmmss");
        String timespan = format.format(new Date());
        String pwd = Md5Encrypt.getMD5(AccountConstants.SHUMI_PWD + timespan).toUpperCase();
        param.put("userid", AccountConstants.SHUMI_ID);
        param.put("timespan", timespan);
        param.put("pwd", pwd);
        String result = HttpUtil.post(UrlConstants.URL_SHUMI_SEND_BALANCE, param);
        return result;
    }


    private static Sms getSms(String mobile, String code, String msgid, int resultCode) {
        Sms sms = new Sms();
        sms.setCode(code);
        sms.setPhone(mobile);
        sms.setResultCode(resultCode);
        sms.setResultSid(msgid);
        sms.setSendtime(DateUtil.date2Timestamp(new Date()));
        return sms;
    }
}
