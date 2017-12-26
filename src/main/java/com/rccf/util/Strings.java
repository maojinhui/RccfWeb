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
        if ("null".equals(data)) {
            return true;
        }
        if("undefined".equals(data)){
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
     * 性别转换
     *
     * @param sex
     * @return
     */
    public static String getInputSex(Integer sex) {
        if (sex == null) {
            return "";
        }
        if (sex == 0) {
            return "未知";
        } else if (sex == 1) {
            return "女";
        } else if (sex == 2) {
            return "男";
        }
        return "";
    }

    /**
     * 婚姻状况
     *
     * @param married
     * @return
     */
    public static String getInputMarried(Integer married) {
        if (married == null) {
            return "";
        }
        if (married == 0) {
            return "未知";
        } else if (married == 1) {
            return "未婚";
        } else if (married == 2) {
            return "已婚";
        }
        return "";
    }

    public static String getInputEducationLevel(Integer education) {
        if (education == null) {
            return "";
        }

        switch (education) {
            case 0:
                return "未知";
            case 1:
                return "小学";
            case 2:
                return "初中";
            case 3:
                return "高中/中专";
            case 4:
                return "专科";
            case 5:
                return "本科";
            case 6:
                return "硕士";
            case 7:
                return "博士";
            case 8:
                return "博士后";
            default:
                return "";
        }
    }

    public static String getCompanyNature(Integer nature) {
        if (nature == null) {
            return "";
        }

        switch (nature) {
            case 0:
                return "未知";
            case 1:
                return "国有企业";
            case 2:
                return "集体企业";
            case 3:
                return "联营企业";
            case 4:
                return "股份合作制企业";
            case 5:
                return "私营企业";
            case 6:
                return "个体工商户";
            case 7:
                return "合伙企业";
            case 8:
                return "有限责任公司";
            case 9:
                return "股份有限公司";
            default:
                return "";
        }
    }

    public static String getTerm(Integer year, Integer month, Integer day) {
        String str = "";
        if (year != null) {
            str += year + "年";
        }
        if (month != null) {
            str += month + "月";
        }
        if (day != null) {
            str += day + "天";
        }
        return str;
    }

    public static String getRepaymentType(Integer type) {
        if (type == null) {
            return "";
        }
        switch (type) {
            case 1:
                return "等额本金";
            case 2:
                return "等额本息";
            case 3:
                return "停本付息";
            case 4:
                return "先息后本";
            default:
                return "";
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

    /**
     * 将123  转化为 ABC
     *
     * @param num
     * @return
     */
    public static String getLevel(int num) {
        if (num <= 0) {
            return "未知";
        }
        int n = num + 64;
        char c = (char) n;
        String str = String.valueOf(c);
        return str;
    }

    public static void main(String args[]){
        char c = (char)65;
       String str =  String.valueOf(c);
        System.out.println(str);
    }


}
