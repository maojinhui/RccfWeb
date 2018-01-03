package com.rccf.util.image;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rccf.model.poster.BPoster;
import com.rccf.util.Strings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class Watermark {


    /**
     * 根据用户上传的信息生成海报
     * @param bPoster
     * @param name
     * @param phone
     * @param targetPath
     * @return
     */
    public static String getWatermarkImage(BPoster bPoster ,  String name , String phone , String targetPath ) {
        String srcImg = bPoster.getBackimg();
        String extra = bPoster.getExtra();
        JSONObject object = JSON.parseObject(extra);
        String img = object.getString("img");
        JSONObject nameObject = JSON.parseObject(object.getString("name")) ;
        JSONObject phoneObject = JSON.parseObject(object.getString("phone")) ;
        Font nameFont = getFont(nameObject);
        Font phoneFont = getFont(phoneObject);
        Color nameColor = getColor(nameObject.getString("color"))  ;
        Color phoneColor = getColor(phoneObject.getString("color"))  ;
        float nameAlpha = Float.valueOf(nameObject.getString("alpha"));
        float phoneAlpha = Float.valueOf(phoneObject.getString("alpha"));
        String nameStr = nameObject.getString("content");
        String phoneStr = phoneObject.getString("content");
//        if(nameStr.contains(":")||nameStr.contains("：")){
//        }else{
//            nameStr = nameStr+":";
//        }
//        if(phoneStr.contains(":")||phoneStr.contains("：")){
//        }else{
//            phoneStr = phoneStr+":";
//        }
        int nameWidth = nameObject.getIntValue("pWidth");
        int nameHeight = nameObject.getIntValue("pHeight");
        int phoneWidth = phoneObject.getIntValue("pWidth");
        int phoneHeight = phoneObject.getIntValue("pHeight");
        if(Strings.isNullOrEmpty(targetPath)){
            targetPath = "/usr/local/tomcat/webapps/web/temp/t_"+new Date().getTime()+".jpg";
        }
        String path   =  drawText(img, nameColor , nameFont, nameAlpha , nameStr+name , nameWidth , nameHeight ,  targetPath);
        String path2  =  drawText(path , phoneColor , phoneFont , phoneAlpha ,phoneStr+phone , phoneWidth,phoneHeight , path );
        return path2;
    }


    /**
     * 图片添加文字
     * @param srcImgPath
     * @param color
     * @param font
     * @param alpha
     * @param text
     * @param pWidth
     * @param pHeight
     * @param targetPath
     * @return
     */
    private static String drawText(String srcImgPath ,Color color , Font font , float alpha ,
                                 String text , int pWidth , int pHeight , String targetPath ){
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(text, pWidth, pHeight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targetPath);
            ImageIO.write(buffImg, "JPG", os);
            return targetPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据Json数据获取字体
     * @param object
     * @return
     */
    private static Font getFont(JSONObject object ){
        String fontName = object.getString("font");
        String fontSize = object.getString("fontSize");
        try {
            int size = Integer.valueOf(fontSize);
            Font font = new Font(fontName , Font.PLAIN , size);
            return font;
        }catch (Exception e){
            return null;
        }
    }


    private static Color getColor(String c){
        String clow = c.toLowerCase();
        if(clow.equals("black")){
            return Color.black;
        }
        else if (clow.equals("blue")){
            return Color.blue;
        }
        else if (clow.equals("cyan")){
            return Color.cyan;
        }
        else if (clow.equals("gray")){
            return Color.gray;
        }
        else if (clow.equals("green")){
            return Color.green;
        }
        else if (clow.equals("magenta")){
            return Color.magenta;
        }
        else if (clow.equals("red")){
            return Color.red;
        }
        else if (clow.equals("white")){
            return Color.white;
        }
        else if (clow.equals("yellow")){
            return Color.yellow;
        }else{
            return Color.black;
        }
    }




}
