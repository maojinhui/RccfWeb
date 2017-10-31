package com.rccf.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by greatland on 17/7/11.
 */
public class DateUtil {

    /**
     * String 2 Date
     *
     * @param dateStr
     * @return
     */
    public static Date string2Date(String dateStr) {
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
//        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * String 2 Date
     *
     * @param dateStr
     * @return
     */
    public static Date string2Date2(String dateStr) {
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
//        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dateStr == null || dateStr.equals("")) {
                return null;
            }
            date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Date 2 String yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * Date 2 String yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2StringSimple(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    /**
     * yyyy-mm-dd hh:mm:ss格式的时间转换成为Timestamp
     *
     * @param tsStr
     * @return
     */
    public static Timestamp string2Timestamp(String tsStr) {
        Timestamp ts = null;
        try {
            ts = Timestamp.valueOf(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ts;
    }


    /**
     * @param ts
     * @return
     */
    public static Date timestamp2Date(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        return new Date(ts.getTime());

    }

    /**
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date) {
        if (date != null) {
            return new Timestamp((date).getTime());
        }
        return null;
    }


    /**
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth(Date date) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * @param strDate 返回java.sql.Date格式的
     */
    public static java.sql.Date strToSqlDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            return null;
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }


    public static void main(String args[]) {
        String lastString = "20170913-776";
        lastString = lastString.substring(lastString.indexOf("-") + 1);
        System.out.println(lastString);
    }


}
