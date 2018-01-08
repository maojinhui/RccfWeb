package com.rccf.controller.accept;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.UrlConstants;
import com.rccf.model.accept.Accepted0105;
import com.rccf.service.BaseService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.excel.ExcelToAccepted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/excel/2/accept" , produces = UrlConstants.PRODUCES)
public class ExcelToAcceptedController {


    private static String fileName = "/Users/greatland/Desktop/data123.xlsx";

    @Autowired
    BaseService baseService;

    @ResponseBody
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws Exception {

String jsonStr = "{\"系统管理员\":\"a001\",\n" +
        "\"陈佳丽\":\"s001\",\n" +
        "\"魏琳琳\":\"s002\",\n" +
        "\"高波\":\"s003\",\n" +
        "\"杨金峰\":\"s004\",\n" +
        "\"祖佳豪\":\"s005\",\n" +
        "\"刘彩芳\":\"e023\",\n" +
        "\"郭志强\":\"e024\",\n" +
        "\"王瑜\":\"e025\",\n" +
        "\"汪亚龙\":\"e026\",\n" +
        "\"王妍\":\"e027\",\n" +
        "\"杨春洪\":\"e028\",\n" +
        "\"田晓鹏\":\"e029\",\n" +
        "\"于淼\":\"e030\",\n" +
        "\"陈小良\":\"e031\",\n" +
        "\"李旭\":\"e032\",\n" +
        "\"宿美玲\":\"e033\",\n" +
        "\"柴林杰\":\"e034\",\n" +
        "\"齐杰\":\"e035\",\n" +
        "\"余琴\":\"e038\",\n" +
        "\"刘兴旺\":\"e036\",\n" +
        "\"李向军\":\"e037\",\n" +
        "\"沈雁冰\":\"e039\",\n" +
        "\"吴钢\":\"e040\",\n" +
        "\"王雪玲\":\"e041\",\n" +
        "\"张小平\":\"e042\",\n" +
        "\"邵建霞\":\"e043\",\n" +
        "\"赵海洋\":\"e044\",\n" +
        "\"张婷\":\"e046\",\n" +
        "\"杨振宇\":\"e047\",\n" +
        "\"郑大鹏\":\"e048\",\n" +
        "\"谢清\":\"e049\",\n" +
        "\"薛瑞亮\":\"e050\",\n" +
        "\"胡海滨\":\"e051\",\n" +
        "\"席晓彪\":\"e052\",\n" +
        "\"陈玉霞\":\"e053\",\n" +
        "\"牛寅锋\":\"e054\",\n" +
        "\"梁志君\":\"e055\",\n" +
        "\"田震\":\"e056\",\n" +
        "\"柏宁宁\":\"e057\",\n" +
        "\"陈超\":\"e058\",\n" +
        "\"宋强\":\"e059\",\n" +
        "\"吕福聪\":\"e060\",\n" +
        "\"田洪英\":\"e061\",\n" +
        "\"邵巧彦\":\"e062\",\n" +
        "\"许景茏\":\"e063\",\n" +
        "\"林圣寓\":\"e064\",\n" +
        "\"战绍辉\":\"e065\",\n" +
        "\"龙俊俊\":\"e066\",\n" +
        "\"闫福强\":\"e067\",\n" +
        "\"齐佳宝\":\"e070\",\n" +
        "\"朱强强\":\"e071\",\n" +
        "\"刘志远\":\"e072\",\n" +
        "\"韩佳佳\":\"e073\",\n" +
        "\"-马勇伟\":\"e074\",\n" +
        "\"苗洪禄\":\"e075\",\n" +
        "\"王芳敏\":\"e076\",\n" +
        "\"闻存存\":\"e077\",\n" +
        "\"赵亚茹\":\"e078\",\n" +
        "\"李军\":\"e079\",\n" +
        "\"王圣豪\":\"e080\",\n" +
        "\"周伟\":\"e081\",\n" +
        "\"孟鹏\":\"e082\",\n" +
        "\"刘衡\":\"e083\",\n" +
        "\"马岩岩\":\"e084\",\n" +
        "\"吴丹\":\"e086\",\n" +
        "\"张志恒\":\"e087\",\n" +
        "\"马威\":\"e088\",\n" +
        "\"王猛\":\"e090\",\n" +
        "\"尹玉川\":\"e092\",\n" +
        "\"赵辉\":\"e0124\",\n" +
        "\"郭来泉\":\"e0136\",\n" +
        "\"董云鹏\":\"e0137\",\n" +
        "\"曹丽成\":\"e0142\",\n" +
        "\"黄志超\":\"e0143\",\n" +
        "\"陈坤\":\"e0144\",\n" +
        "\"郭欣\":\"e0145\",\n" +
        "\"陈书涛\":\"e0146\",\n" +
        "\"雷志学\":\"e0147\",\n" +
        "\"郭立华\":\"e0148\",\n" +
        "\"李由\":\"e0149\",\n" +
        "\"席颖雪\":\"e0150\",\n" +
        "\"武丽\":\"e0151\",\n" +
        "\"邢舫\":\"e0152\",\n" +
        "\"童晓东\":\"e0153\",\n" +
        "\"李嫚\":\"e0154\",\n" +
        "\"陈韵灿\":\"e0155\",\n" +
        "\"李文龙\":\"e0156\",\n" +
        "\"牛皓宇\":\"e0157\",\n" +
        "\"阿良\":\"a002\",\n" +
        "\"人事\":\"a003\",\n" +
        "\"杜永和\":\"e0158\",\n" +
        "\"李小勇\":\"e0159\",\n" +
        "\"刘巍巍\":\"e0160\",\n" +
        "\"李双春\":\"e0161\",\n" +
        "\"李文龙\":\"a002\",\n" +
        "\"李超\":\"e0162\",\n" +
        "\"柳志良\":\"e0163\",\n" +
        "\"人事部\":\"e0164\",\n" +
        "\"胡若楠\":\"e0165\",\n" +
        "\"赵金静\":\"e0166\",\n" +
        "\"张艳红\":\"e0167\",\n" +
        "\"郭春爽\":\"e0168\",\n" +
        "\"张爽\":\"e0169\",\n" +
        "\"测试员工\":\"e0170\",\n" +
        "\"陈佩志\":\"e0171\",\n" +
        "\"马晓龙\":\"e0172\",\n" +
        "\"王海涛\":\"e0173\",\n" +
        "\"范奎东\":\"e0174\",\n" +
        "\"\":\"e0175\",\n" +
        "\"马勇伟\":\"e0176\",\n" +
        "\"闫晗玉\":\"e0177\",\n" +
        "\"丁权\":\"e0178\",\n" +
        "\"宋琛\":\"e0179\",\n" +
        "\"张振宇\":\"e0180\",\n" +
        "\"刘亚蒙\":\"e0181\",\n" +
        "\"杨亮\":\"e0182\",\n" +
        "\"白琳\":\"e0183\",\n" +
        "\"宋毛旦\":\"e0184\",\n" +
        "\"超级管理员\":\"A01\",\n" +
        "\"崔志强\":\"e0185\",\n" +
        "\"邢鹏飞\":\"e0186\",\n" +
        "\"靳凯\":\"e0187\",\n" +
        "\"高君宝\":\"e0188\",\n" +
        "\"总经办\":\"Z01\",\n" +
        "\"芦倩\":\"X01\"}";

        JSONObject employeeObject = JSON.parseObject(jsonStr);
//        Set<String> keySet = object.keySet();
//        JSONObject employeeObject = new JSONObject();
//        for (String  key: keySet ) {
//            String value = object.getString(key);
//            Integer integer = Integer.valueOf(key);
//            employeeObject.put(value,integer);
//        }

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
            acc.setWantMoney(Double.valueOf(cellValue.get(8).toString()));
            acc.setLoanMoney(Double.valueOf(cellValue.get(9).toString()));
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
                    acc.setClerk(employeeObject.getString(saleman));
                }

            }
            String directorName = cellValue.get(15).toString();
            if(!Strings.isNullOrEmpty(directorName)){
//                acc.setdir(directorName);
                if(employeeObject.containsKey(directorName)){
                    acc.setDirector(employeeObject.getString(directorName));
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

        baseService.save(acc);
        }

        return ResponseUtil.success();
    }



}
