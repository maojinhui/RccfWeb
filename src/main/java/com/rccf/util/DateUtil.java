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

    /**
     * timestamp 转换 年月
     *
     * @param timestamp
     * @return
     */
    public static String timesstapToStringMD(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        Date date = timestamp2Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
        return dateFormat.format(date);
    }

    /**
     * timestamp 转换 年月
     *
     * @param date
     * @return
     */
    public static String dateToStringMD(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
        return dateFormat.format(date);
    }


    /**
     * 判断两个日期是否是同一天
     *
     * @param date1 date1
     * @param date2 date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }


    public static void main(String args[]) {
//        String lastString = "20170913-776";
//        lastString = lastString.substring(lastString.indexOf("-") + 1);
//        System.out.println(lastString);

        Date date = new Date(string2Date("2017-01-01").getTime());
//        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
        System.out.println("args = [" + dateFormat.format(date) + "]");

    }


}
