package com.rccf.test;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

public class TestMarvin {

    MarvinImage window = MarvinImageIO.loadImage("E:\\msC_desktop\\batlogo.jpg");


    public static void main(String[] args) {
        String srcFile = "c://img//b.jpg";
        String dstFile = "c://img//bb.jpg";
        MarvinImage image = MarvinImageIO.loadImage(srcFile);
        MarvinImage backupImage = image.clone();
        backupImage.resize(800, 600);
        MarvinImageIO.saveImage(backupImage, dstFile);
    }

}
