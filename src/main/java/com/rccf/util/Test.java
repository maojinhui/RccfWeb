package com.rccf.util;

//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConfiguration;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


import java.util.regex.Pattern;

/**
 * Created by greatland on 17/7/18.
 */

public class Test {


//    public static void convert(String input , String output){
//        File inputFile = new File(input);
//        File outputFile = new File(output);
//        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
//        try{
//            connection.connect();
//            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//            converter.convert(inputFile, outputFile);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try{
//                if(connection != null){
//                    connection.disconnect();
//                    connection = null;
//                }
//            }catch(Exception e){}
//        }
//    }





//    public static void convertFile(String inputFilePath){
////        DefaultOfficeManagerConfiguration defaultOfficeManagerConfiguration;
//        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
//        OpenOfficeConfiguration configuration = new OpenOfficeConfiguration(connection);
//
//
//
//    }


    public static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll(".doc", ".pdf");
        return outputFilePath;
    }

    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice4/";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "D:/Program Files/OpenOffice.org 3";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.app/Contents/";
        }
        return null;
    }


    public static void main(String args[]){
        String inputFilePath = "/Users/greatland/Desktop/产品编号-1.xlsx";
//        try {
//
//            word2pdf(inputFilePath);
//
//        } catch (OfficeException e) {
//            e.printStackTrace();
//        }
//        JodConverter
//                .convert(inputFile)
//                .to(outputFile)
//                .execute();

    }




}
