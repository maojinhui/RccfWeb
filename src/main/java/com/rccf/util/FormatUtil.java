package com.rccf.util;

import java.text.NumberFormat;

public class FormatUtil {


    /**
     * @param count
     * @param data
     * @return
     */
    public static String formatDouble(int count, double data) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(count);
        return nf.format(data);
    }

    /**
     * 格式化double两位小数
     *
     * @param data
     * @return
     */
    public static String formatDouble2(double data) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(data);
    }

}
