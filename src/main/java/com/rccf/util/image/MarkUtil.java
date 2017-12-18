package com.rccf.util.image;


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




}
