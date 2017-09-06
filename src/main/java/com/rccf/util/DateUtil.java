package com.rccf.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                return new Date(0);
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


    public static void main(String args[]) {
        Date date = string2Date("2017-01-01");
        System.out.println(date);
    }


}
