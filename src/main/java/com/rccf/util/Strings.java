package com.rccf.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by greatland on 17/7/13.
 */
public class Strings {

    /**
     * 判断字符串是否为NULL 或者 是empty
     *
     * @param data
     * @return
     */
    public static boolean isNullOrEmpty(String data) {

        if (null == data) {
            return true;
        }
        if (StringUtils.isEmpty(data)) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if(null == mobiles ){
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }
}
