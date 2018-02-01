package com.rccf.util.file;

import com.rccf.constants.build.DebugManager;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class BackCustomerImg {


    /**
     * 复制文件
     * @param customerFileUrl
     * @return
     */
    public static String copyFile(String customerFileUrl){
        int position = customerFileUrl.lastIndexOf("/");
        String fileName = customerFileUrl.substring(position+1);
        String host = "";
        String path = "";
        String backUp = "backup/";
        if(DebugManager.DEBUG){
            host = DebugManager.LOCAL_HOST_ADDRESS;
            path = DebugManager.LOCAL_IMAGE_PATH;
        }else{
            host = DebugManager.SERVER_HOST_ADDRESS;
            path = DebugManager.SERVER_IMAGE_PATH;
        }

        try {
            File  inputFile = new File(path+fileName);
            File  outputFile = new File(path+backUp+fileName);
            File  outputParent = outputFile.getParentFile();
            if(outputParent.toString().endsWith(File.separator)){
            }else{
                outputParent = new File(outputParent.toString()+File.separator);
            }

            if(!outputParent.exists()){
                outputParent.mkdirs();
            }
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }else{
                return host+backUp+fileName;
            }
            FileInputStream  inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            IOUtils.copy(inputStream,outputStream);
            return host+backUp+fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        String str = "/temp/whatthefuck.png";
        int position = str.lastIndexOf("/");
        String  hostAddress = str.substring(0,position+1);


        System.out.println(position);
        System.out.println("start:"+str.substring(position+1));
        System.out.println("second:"+hostAddress);


    }


}
