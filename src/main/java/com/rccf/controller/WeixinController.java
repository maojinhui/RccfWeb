package com.rccf.controller;

import com.rccf.model.Subcribe;
import com.rccf.model.Unsubcribe;
import com.rccf.model.result.WeixinMessage;
import com.rccf.service.SubcribeService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.WeixinUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by greatland on 17/7/18.
 */
@Controller
@RequestMapping(value = "/weixin", produces = {"text/html;charset=UTF-8;"})
public class WeixinController {


    Logger logger=Logger.getLogger(WeixinController.class);

    @Autowired
    private SubcribeService subcribeService;



    @ResponseBody
    @RequestMapping(value = "/gettoken")
    public String getToken() {
        String accessToken = WeixinUtil.getAccessToken();
        return ResponseUtil.success(accessToken);
    }


    /**
     * 处理公众平台
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/msg_event")
    public String envent(HttpServletRequest request) {
        String echostr = request.getParameter("echostr");
        if (Strings.isNullOrEmpty(echostr)) {
            try {
                InputStream inputStream = request.getInputStream();
                String data = IOUtils.toString(inputStream);
                logger.error(data);
                WeixinMessage message = WeixinUtil.getMessage(data);
                logger.error(message.getMsgType()+"="+message.getEvent());
                if (null == message) {
                    return "success";
                }else{
                    String fromUserName = message.getFromUserName();
                    String toUserName = message.getToUserName();
                    String ticket = message.getTicket();
                    if (message.getMsgType().equals("event")){//事件处理方法
                        if (message.getEvent().equals("subscribe")){//用户关注公众号
                            Subcribe subcribe=new Subcribe();
                            subcribe.setOpenid(fromUserName);
                            subcribe.setCreateTime(DateUtil.date2Timestamp(new Date()));
                            subcribe.setTicket(ticket);
                            subcribeService.saveSubcribe(subcribe);
                            WeixinMessage msg = new WeixinMessage();
                            msg.setFromUserName(toUserName);
                            msg.setToUserName(fromUserName);
                            msg.setCreateTime(new Date().getTime()/1000);
                            msg.setContent("欢迎关注融成金服！");
                            msg.setMsgType("text");
                            String xml= WeixinUtil.getXml(msg);
                            return xml;
                        }else if (message.getEvent().equals("unsubscribe")){//用户取消关注公众号
                            Unsubcribe subcribe=new Unsubcribe();
                            subcribe.setOpenid(fromUserName);
                            subcribe.setCreateTime(DateUtil.date2Timestamp(new Date()));
                            subcribeService.saveUnsubcribe(subcribe);
                        }else{

                        }

                    }else if (message.getMsgType().equals("text")){//文本信息处理方法



                    }
                    return "success";
                }


            } catch (IOException e) {
                e.printStackTrace();
                return "success";
            }
        } else {//当数据中有echostr参数表明是验证信息
            return echostr;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/menu_create")
    public String creatMenu() {
        String result = WeixinUtil.creatMenu();
        return result;
    }


}
