package com.rccf.util;

import com.alibaba.fastjson.JSON;
import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.AccountConstants;
import com.rccf.model.result.WeixinMessage;
import com.rccf.model.result.WxJsapiticket;
import com.rccf.model.result.Wxtoken;
import com.rccf.thirdimpl.MyXppDriver;
import com.rccf.util.network.HttpUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by greatland on 17/7/18.
 */
public class WeixinUtil {


    private static String MEMCACHED_TOKEN_KEY = "access_token_weixin";

    private static String MEMCACHED_JSAPI_TICKET = "jsapi_ticket_weixin";

    private static String grant_type_token = "client_credential";

    private static String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    private static String URL_MENU_CREAT = "https://api.weixin.qq.com/cgi-bin/menu/create";

    private static String URL_JSAPI_TICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&";


    /**
     * 获取微信请求到的accesstoken
     *
     * @return
     */
    public static String getAccessToken() {
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        String token = (String) spyMemcachedManager.get(MEMCACHED_TOKEN_KEY);
        if (null != token) {
            return token;
        } else {
            token = getAccessTokenFromNet();
        }
        return token;
    }

    /**
     * 当access_token出错的时候移除缓存的token
     */
    public static void removeAccesstoken(){
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        spyMemcachedManager.delete(MEMCACHED_TOKEN_KEY);
    }

    /**
     * 当access_token过期后重新请求
     *
     * @return
     */
    private static String getAccessTokenFromNet() {
        String url = URL_TOKEN + "?grant_type=" + grant_type_token + "&appid=" + AccountConstants.WEIXIN_APPID + "&secret=" + AccountConstants.WEIXIN_APPSECRET;
        String result = HttpUtil.get(url);
        Wxtoken wxtoken = JSON.parseObject(result, Wxtoken.class);
        String token = wxtoken.getAccess_token();
        int expire = wxtoken.getExpires_in();
        try {
            SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
            spyMemcachedManager.set(MEMCACHED_TOKEN_KEY, token, expire);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 获取微信请求到的JSAPITICKET
     *
     * @return
     */
    public static String getAccessJSAPI_TICKET() {
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        String token = (String) spyMemcachedManager.get(MEMCACHED_JSAPI_TICKET);
        if (null != token) {
            return token;
        } else {
            token = getJSAPI_TICKETFromNet();
        }
        return token;
    }

    /**
     * JSAPITICKET
     */
    public static void removeJSAPI_TICKET(){
        SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
        spyMemcachedManager.delete(MEMCACHED_JSAPI_TICKET);
    }

    /**
     * 当JSAPITICKET过期后重新请求
     *
     * @return
     */
    private static String getJSAPI_TICKETFromNet() {
        String url = URL_JSAPI_TICKET + "access_token="+getAccessToken();
        String result = HttpUtil.get(url);
        WxJsapiticket wxJsapiticket = JSON.parseObject(result, WxJsapiticket.class);
        String ticket = wxJsapiticket.getTicket();
        int expire = wxJsapiticket.getExpires_in();
        try {
            SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) SpringContextUtil.getBean("memcachedManager");
            spyMemcachedManager.set(MEMCACHED_JSAPI_TICKET, ticket, expire);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }




    public static String creatMenu() {
        String url = URL_MENU_CREAT + "?access_token=" + getAccessToken();
        String menu = "{\"button\":[{\"type\":\"view\",\"name\":\"营销工具\",\"url\":\"http://weixin.rccfkg.com/advert/weixin01\"},{\"name\":\"关于我们\",\"subbutton\":[{\"type\":\"view\",\"name\":\"产品介绍\",\"url\":\"http://rccfkg.com/paymentService/productDisplay.html\"},{\"type\":\"view\",\"name\":\"联系我们\",\"url\":\"http://rccfkg.com/contact.html\"}]}]}";
        String result = HttpUtil.postJson(url, menu);
        return result;
    }


    /**
     * 根据数据获取message
     * @param data
     * @return
     */
    public static WeixinMessage getMessage(String data){
        XStream stream = new XStream(new MyXppDriver());
        stream.processAnnotations(WeixinMessage.class);
        stream.setClassLoader(WeixinMessage.class.getClassLoader());
        WeixinMessage message = (WeixinMessage) stream.fromXML(data);
        return message;

    }

    /**
     * 根据message来生成xml
     * @param message
     * @return
     */
    public static String getXml(WeixinMessage message){
        XStream stream = new XStream(new MyXppDriver());
        stream.processAnnotations(WeixinMessage.class);
        stream.setClassLoader(WeixinMessage.class.getClassLoader());
        return stream.toXML(message);
    }







}
