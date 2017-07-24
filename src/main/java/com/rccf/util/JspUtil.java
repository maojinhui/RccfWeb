package com.rccf.util;

import javax.servlet.http.HttpServletRequest;

public class JspUtil {

    public static String getUrl(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);

        if(request.getQueryString()!=null)
        {
            url+="?"+request.getQueryString();
        }
        return url;
    }
}
