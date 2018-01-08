package com.rccf.util.excel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rccf.model.accept.Accepted0105;

import com.rccf.util.Strings;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExcelToAccepted {

//    private Logger logger = Logger.getLogger(this.getClass());
    private Workbook wb;
    private Sheet sheet;
    private Row row;


    private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");

    private static String fileName = "/Users/greatland/Desktop/data123.xlsx";


    public ExcelToAccepted(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
//            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
//            logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 0; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }


    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
//                         cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
//                        Date date = cell.getDateCellValue();
//                        cellvalue = date;
                        if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                            Date theDate = cell.getDateCellValue();
                            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            cellvalue = dff.format(theDate);
                        }else{
                            DecimalFormat df = new DecimalFormat("0");
                            cellvalue = df.format(cell.getNumericCellValue());
                        }

                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }


    public static void main(String[] args) throws Exception {
        String jsonStr = "\n" +
                "{\"1\":\"系统管理员\",\n" +
                "\"2\":\"陈佳丽\",\n" +
                "\"3\":\"魏琳琳\",\n" +
                "\"4\":\"高波\",\n" +
                "\"5\":\"杨金峰\",\n" +
                "\"6\":\"祖佳豪\",\n" +
                "\"7\":\"刘彩芳\",\n" +
                "\"8\":\"郭志强\",\n" +
                "\"9\":\"王瑜\",\n" +
                "\"10\":\"汪亚龙\",\n" +
                "\"11\":\"王妍\",\n" +
                "\"12\":\"杨春洪\",\n" +
                "\"13\":\"田晓鹏\",\n" +
                "\"14\":\"于淼\",\n" +
                "\"15\":\"陈小良\",\n" +
                "\"16\":\"李旭\",\n" +
                "\"17\":\"宿美玲\",\n" +
                "\"18\":\"柴林杰\",\n" +
                "\"19\":\"齐杰\",\n" +
                "\"20\":\"余琴\",\n" +
                "\"21\":\"刘兴旺\",\n" +
                "\"22\":\"李向军\",\n" +
                "\"24\":\"沈雁冰\",\n" +
                "\"25\":\"吴钢\",\n" +
                "\"26\":\"王雪玲\",\n" +
                "\"27\":\"张小平\",\n" +
                "\"28\":\"邵建霞\",\n" +
                "\"29\":\"赵海洋\",\n" +
                "\"31\":\"张婷\",\n" +
                "\"32\":\"杨振宇\",\n" +
                "\"33\":\"郑大鹏\",\n" +
                "\"34\":\"谢清\",\n" +
                "\"35\":\"薛瑞亮\",\n" +
                "\"36\":\"胡海滨\",\n" +
                "\"37\":\"席晓彪\",\n" +
                "\"38\":\"陈玉霞\",\n" +
                "\"39\":\"牛寅锋\",\n" +
                "\"40\":\"梁志君\",\n" +
                "\"41\":\"田震\",\n" +
                "\"42\":\"柏宁宁\",\n" +
                "\"43\":\"陈超\",\n" +
                "\"44\":\"宋强\",\n" +
                "\"45\":\"吕福聪\",\n" +
                "\"46\":\"田洪英\",\n" +
                "\"47\":\"邵巧彦\",\n" +
                "\"48\":\"许景茏\",\n" +
                "\"49\":\"林圣寓\",\n" +
                "\"50\":\"战绍辉\",\n" +
                "\"51\":\"龙俊俊\",\n" +
                "\"52\":\"闫福强\",\n" +
                "\"55\":\"齐佳宝\",\n" +
                "\"56\":\"朱强强\",\n" +
                "\"57\":\"刘志远\",\n" +
                "\"58\":\"韩佳佳\",\n" +
                "\"59\":\"-马勇伟\",\n" +
                "\"60\":\"苗洪禄\",\n" +
                "\"61\":\"王芳敏\",\n" +
                "\"62\":\"闻存存\",\n" +
                "\"63\":\"赵亚茹\",\n" +
                "\"64\":\"李军\",\n" +
                "\"65\":\"王圣豪\",\n" +
                "\"66\":\"周伟\",\n" +
                "\"67\":\"孟鹏\",\n" +
                "\"68\":\"刘衡\",\n" +
                "\"69\":\"马岩岩\",\n" +
                "\"71\":\"吴丹\",\n" +
                "\"72\":\"张志恒\",\n" +
                "\"73\":\"马威\",\n" +
                "\"74\":\"王猛\",\n" +
                "\"76\":\"尹玉川\",\n" +
                "\"107\":\"赵辉\",\n" +
                "\"119\":\"郭来泉\",\n" +
                "\"120\":\"董云鹏\",\n" +
                "\"126\":\"曹丽成\",\n" +
                "\"127\":\"黄志超\",\n" +
                "\"128\":\"陈坤\",\n" +
                "\"129\":\"郭欣\",\n" +
                "\"130\":\"陈书涛\",\n" +
                "\"131\":\"雷志学\",\n" +
                "\"132\":\"郭立华\",\n" +
                "\"133\":\"李由\",\n" +
                "\"134\":\"席颖雪\",\n" +
                "\"135\":\"武丽\",\n" +
                "\"136\":\"邢舫\",\n" +
                "\"137\":\"童晓东\",\n" +
                "\"138\":\"李嫚\",\n" +
                "\"139\":\"陈韵灿\",\n" +
                "\"140\":\"李文龙\",\n" +
                "\"141\":\"牛皓宇\",\n" +
                "\"142\":\"阿良\",\n" +
                "\"143\":\"人事\",\n" +
                "\"144\":\"杜永和\",\n" +
                "\"146\":\"李小勇\",\n" +
                "\"147\":\"刘巍巍\",\n" +
                "\"148\":\"李双春\",\n" +
                "\"149\":\"李文龙\",\n" +
                "\"150\":\"李超\",\n" +
                "\"151\":\"柳志良\",\n" +
                "\"152\":\"人事部\",\n" +
                "\"153\":\"胡若楠\",\n" +
                "\"155\":\"赵金静\",\n" +
                "\"156\":\"张艳红\",\n" +
                "\"157\":\"郭春爽\",\n" +
                "\"158\":\"张爽\",\n" +
                "\"159\":\"测试员工\",\n" +
                "\"160\":\"陈佩志\",\n" +
                "\"161\":\"马晓龙\",\n" +
                "\"162\":\"王海涛\",\n" +
                "\"163\":\"范奎东\",\n" +
                "\"165\":\"马勇伟\",\n" +
                "\"166\":\"闫晗玉\",\n" +
                "\"167\":\"丁权\",\n" +
                "\"168\":\"宋琛\",\n" +
                "\"169\":\"张振宇\",\n" +
                "\"170\":\"刘亚蒙\",\n" +
                "\"171\":\"杨亮\",\n" +
                "\"172\":\"白琳\",\n" +
                "\"173\":\"宋毛旦\",\n" +
                "\"174\":\"超级管理员\",\n" +
                "\"175\":\"崔志强\",\n" +
                "\"176\":\"邢鹏飞\",\n" +
                "\"177\":\"靳凯\",\n" +
                "\"178\":\"高君宝\",\n" +
                "\"179\":\"总经办\",\n" +
                "\"180\":\"芦倩\"\n" +
                "}";

        JSONObject object = JSON.parseObject(jsonStr);
        Set<String> keySet = object.keySet();
        JSONObject employeeObject = new JSONObject();
        for (String  key: keySet ) {
            String value = object.getString(key);
            Integer integer = Integer.valueOf(key);
            employeeObject.put(value,integer);
        }

        ExcelToAccepted accepted = new ExcelToAccepted(fileName);
        // 对读取Excel表格内容测试
        Map<Integer, Map<Integer,Object>> map = accepted.readExcelContent();
        System.out.println("获得Excel表格的内容:"+map.size()+"==");

        for (int i = 0; i <= map.size(); i++) {
            System.out.println(map.get(i));
            Map<Integer , Object> cellValue =  map.get(i);
            Accepted0105 acc = new Accepted0105();

            acc.setAcceptTime(com.rccf.util.DateUtil.string2Timestamp(cellValue.get(0).toString()));
            acc.setLetterNumber(cellValue.get(1).toString());
            acc.setAcceptedNumber(cellValue.get(2).toString());
            acc.setCustomerName(cellValue.get(3).toString());
//            acc.setCustomerPhone(cellValue.get(4).toString());
            if(!Strings.isNullOrEmpty(cellValue.get(4).toString())){
                Double doubleValue = Double.valueOf(cellValue.get(4).toString());
                BigDecimal bd1 = new BigDecimal(doubleValue);
                acc.setCustomerPhone(bd1.toPlainString());
//                System.out.println(bd1.toPlainString());
//                System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
//                System.out.println("普通数字");
            }






            acc.setBusinessNature(cellValue.get(5).toString());
            acc.setAgency(cellValue.get(6).toString());
            String type = cellValue.get(7).toString();
            int bType = -1;
            if(type.equals("抵押")){
                bType = 1;
            }else if(type.equals("质押")){
                bType = 2;
            }else if (type.equals("信用贷")){
                bType = 0;
            }else if (type.equals("权证")){
                bType = 10;
            }else{
                bType = -1;
            }
            acc.setBusinessType(bType);
            try{
                if(cellValue.get(8) != null ){
                    acc.setWantMoney(Double.valueOf(cellValue.get(8).toString()));
                }
                if(cellValue.get(9) != null ){
                    acc.setLoanMoney(Double.valueOf(cellValue.get(9).toString()));
                }

            }catch (Exception e){

            }

            int service_ = 0 ;
            String service_str = cellValue.get(10).toString();
            if (service_str.equals("有")){
                service_=1;
            }
            acc.setServiceAgreement(service_);

            String service_code = cellValue.get(11).toString();
            if(!Strings.isNullOrEmpty(service_code)){
                acc.setAgreementNumber(service_code);
            }
            String service_fee = cellValue.get(13).toString();
            if(!Strings.isNullOrEmpty(service_fee)){
                acc.setServiceFeeActual(Double.valueOf(service_fee));
            }else{
                acc.setServiceFeeActual(0d);
            }

            String saleman = cellValue.get(14).toString();
            if(!Strings.isNullOrEmpty(saleman)){
                acc.setClerkName(saleman);
               if(employeeObject.containsKey(saleman)){
                   acc.setClerk(employeeObject.getIntValue(saleman)+"");
               }

            }
            String directorName = cellValue.get(15).toString();
            if(!Strings.isNullOrEmpty(directorName)){
//                acc.setdir(directorName);
                if(employeeObject.containsKey(directorName)){
                    acc.setDirector(employeeObject.getIntValue(directorName)+"");
                }
            }
            String houqi = cellValue.get(16).toString();
            if(!Strings.isNullOrEmpty(houqi)){
                acc.setHouqi(houqi);
            }

            String end_date = cellValue.get(17).toString();
            if(!Strings.isNullOrEmpty(end_date)){
                acc.setEndDate(com.rccf.util.DateUtil.string2Timestamp(end_date));
            }
            acc.setState(2);




        }


    }


}
