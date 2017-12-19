package com.rccf.util.image;


import java.awt.*;
import java.io.File;

public class MarkUtil {


    public static String firstImage(String backimg , String  targetPath ,  String name  , String phone){
        String targerTextPath = targetPath;
        String srcImgPath = backimg;
        try{
            File file = new File(targerTextPath);
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }

            String text1 = "我是贷款经理"+name;
            String text2 = "电话:"+phone;
            String text3 = "速速来电，我等你~";

        System.out.println("给图片添加水印文字开始..."+System.currentTimeMillis());
        String target1 = ImageRemarkUtil.markImageByText(text1, srcImgPath, targerTextPath, null, 180, 770);
        String target2 = ImageRemarkUtil.markImageByText(text2, target1, targerTextPath, null, 180, 830);
        String target3 = ImageRemarkUtil.markImageByText(text3, target2, targerTextPath, null, 180, 885);

        return target3;
        }catch (Exception e){
                return null;
        }finally {
            if(targerTextPath!=null){

            }
        }
    }



    public static String secondImage(String backimg , String targetPath , String name , String phone){
        Font font = new Font("宋体",Font.PLAIN,22);
        String targerTextPath = targetPath;
        String srcImgPath = backimg;

        try{
            File file = new File(targerTextPath);
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }

            String text1 = "您的服务管家:"+name;
            String text2 = "联系电话:"+phone;
            String address = "北京市朝阳区建外SOHO西区15号楼20层";


            System.out.println("给图片添加水印文字开始..."+System.currentTimeMillis());
            String target1 = ImageRemarkUtil.markImageByText(text1, srcImgPath, font,  targerTextPath, null, 188, 980);
            String target2 = ImageRemarkUtil.markImageByText(text2, target1, font, targerTextPath, null, 188, 1030);
            String target3 = ImageRemarkUtil.markImageByText(address, target2,font,  targerTextPath, null, 188, 1080);

            return target3;
        }catch (Exception e){
            return null;
        }finally {
            if(targerTextPath!=null){

            }
        }


    }



}
