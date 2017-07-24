package com.rccf.util;

import com.rccf.model.result.WeixinMessage;
import com.rccf.model.result.Wxtoken;
import com.rccf.thirdimpl.MyXppDriver;
import com.rccf.util.encrypt.ShaEncript;
import com.thoughtworks.xstream.XStream;

/**
 * Created by greatland on 17/7/18.
 */

public class Test {


    public static void main(String args[]){

//        try {
//            String rccf = ShaEncript.encryptSHA("rccf");
//            System.out.println(rccf);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        WeixinMessage message = new WeixinMessage();
//        message.setContent("我的天");
//        message.setMsgId("123456");
        XStream stream = new XStream(new MyXppDriver());
        stream.processAnnotations(WeixinMessage.class);
//        String xml = stream.toXML(message);
//        System.out.println(xml);
        String data = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";
        WeixinMessage message = (WeixinMessage) stream.fromXML(data);
        System.out.println(message.getFromUserName());

    }

}
