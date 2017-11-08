package com.rccf.util;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.NumberFormat;
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
        if ("null".equals(data)){
            return true;
        }

        return false;
    }


    /**
     * 判断是否是手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (null == mobiles) {
            return false;
        }
        Pattern p = Pattern.compile("^1[34578]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }


    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 手机号码格式化微182****5716
     *
     * @param phone
     * @return
     */
    public static String phoneNumberFormat(String phone) {
        String reStr = phone.substring(phone.length() - 4, phone.length());
        String preStr = phone.substring(0, phone.length() - 8);
        StringBuilder sb = new StringBuilder();
        sb.append(preStr).append("****").append(reStr);
        return sb.toString();
    }


    /**
     * @param o
     * @return
     */
    public static String getInputString(Object o) {
        if (null == o) {
            return "";
        }
        return o.toString();
    }

    public static String getInputString(Object o, Object data) {
        if (o == null) {
            return "";
        } else {
            if (data == null) {
                return "";
            } else {
                return data.toString();
            }
        }
    }


    /**
     * 根据小数计算出百分比
     *
     * @param d
     * @return
     */
    public static String getPercentString(Double d) {
        if (d == null) {
            return "";
        }
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        String percent = percentFormat.format(d);
        return percent;

    }




}
