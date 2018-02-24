package com.rccf.test;

import com.google.common.base.Splitter;
import com.rccf.model.gzh.accpet.AcceptedTemp;
import com.rccf.util.Strings;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Iterator;

public class TestGuava {


    public static void main(String[] args) {

//        String str = "AcceptedTemp{id=ff808081614f487a01614f72b1310000, customerId=ff80808160e52cd90160e843f679002b, customerName=金地中心, customerPhone=18811168881, customerIdcard=142635198909163410, customerLoanType=1, customerFile=[\"/temp/backup/3accd07c-faef-43.png\",\"/temp/backup/ad6ffc30-e38c-4e.jpg\",\"/temp/backup/e01f39dc-5bb1-47.jpg\"], employee=T001, deputy=e023, director=s001, customerWantmoney=125.0, serviceProportion=1.4, houqi=154, produceInfo=[{\"rowNo\":0,\"agency_name\":\"融成\",\"code\":\"1.2.1-DY-RC-JCD\",\"name\":\"加成贷\",\"create_person\":154,\"id\":31,\"type\":0}], channelFee=渠道, materialFee=材料, sanfangFee=三方, state=3, employeeName=Admin, deputyName=刘彩芳, directorName=陈佳丽, houqiName=市场专员, acceptTime=null, content=退回吧, acceptNumber=20180206-824, acceptId=null, letterNumber=2.1XY-YGD, createTime=null}";
        String str = "AcceptedTemp{id=ff808081614f487a01614f72b1310000, customerId=ff80808160e52cd90160e843f679002b, customerName=金地中心, customerPhone=18811168881, customerIdcard=142635198909163410, customerLoanType=1, customerFile=[\"/temp/backup/3accd07c-faef-43.png\",\"/temp/backup/ad6ffc30-e38c-4e.jpg\",\"/temp/backup/e01f39dc-5bb1-47.jpg\"], employee=T001, deputy=e023, director=s001, customerWantmoney=125.0, serviceProportion=1.4, houqi=154, produceInfo=[{\"rowNo\":0,\"agency_name\":\"融成\",\"code\":\"1.2.1-DY-RC-JCD\",\"name\":\"加成贷\",\"create_person\":154,\"id\":31,\"type\":0}], channelFee=渠道, materialFee=材料, sanfangFee=三方, state=5, employeeName=Admin, deputyName=刘彩芳, directorName=陈佳丽, houqiName=市场专员, acceptTime=2018-02-06 00:00:00.0, content=退回吧, acceptNumber=20180206-825, acceptId=1388, letterNumber=2.1XY-YGD, createTime=null}";
        str = getObjectString(str);
        System.out.println(str);
        Iterable<String> split = Splitter.on(',').split(str);
        AcceptedTemp acceptedTemp = new AcceptedTemp();
        for (Iterator iter = split.iterator(); iter.hasNext(); ) {
            String value = (String) iter.next();
            Iterable<String> split1 = Splitter.on('=').split(value);
            Iterator<String> iterator = split1.iterator();
            String attributeKey = iterator.next().trim();
            String attributeValue = iterator.next().trim();

        }

        System.out.println(acceptedTemp);

    }

    private static String getObjectString(String objectToString) {
        String value = objectToString.substring(objectToString.indexOf('{') + 1,
                objectToString.lastIndexOf("}"));
        return value;
    }

}
