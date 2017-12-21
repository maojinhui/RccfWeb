package com.rccf.util.image;

import java.awt.*;

public class GetLocalFontFamily {


    public static void main(String[] args) {
        //获取系统中可用的字体的名字
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();

        for (int i = 0; i < fontName.length; i++) {
            System.out.println(fontName[i]);
        }


    }


}
