package com.rccf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * Created by greatland on 17/7/7.
 */
public class ResponseUtil {


    /**
     * 不需要返回数据的成功
     * @return
     */
    public static String success(){
        return  success_response(null);
    }




    /**
     * 带列表数据的返回
     * @param objects
     * @return
     */
    public static String success(List<Object> objects){
       String json = JSON.toJSONString(objects);
        return success_response(json);
    }

    /**
     * 返回一条数据
     * @param o
     * @return
     */
    public static String success(Object o){
        return success_response(JSON.toJSONString(o, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 返回成功
     * @param data
     * @return
     */
    private static String success_response(String data){
        JSONObject object=new JSONObject();
        object.put("code",1);
        object.put("state","success");
        object.put("errormsg","");
        if(null != data){
            object.put("data",data);
        }
        return object.toJSONString();
    }


    /**
     * 返回错误信息
     * @param code
     * @param errormsg
     * @return
     */
    public static String fail(String code,String errormsg){
        JSONObject object=new JSONObject();
        object.put("code",code);
        object.put("state","fail");
        object.put("errormsg",errormsg);
        return  object.toJSONString();
    }

    /**
     * 返回错误信息
     *
     * @return
     */
    public static String fail(){
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("state","fail");
        object.put("errormsg","内部错误");
        return  object.toJSONString();
    }

}
