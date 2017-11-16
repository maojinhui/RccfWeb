package com.rccf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.servlet.ModelAndView;

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
        if(o instanceof  String){
            return success_response(o.toString());
        }else {
            return success_response(JSON.toJSONString(o, SerializerFeature.WriteMapNullValue));
        }
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
     * 返回列表数据
     * @param count
     * @param everypage
     * @param list
     * @return
     */
    public static String success_list(int count , int everypage,List list){
        JSONObject object=new JSONObject();
        object.put("code",1);
        object.put("state","success");
        object.put("errormsg","");
        object.put("total",count);
        object.put("every",everypage);
        String data = JSON.toJSONString(list);
        if (null == list || list.size()==0){
            object.put("data","[]");
        }else{
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
    public static String fail(int code,String errormsg){
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

    public static String success_jsonp(String callback, Object o) {

        if (o instanceof JSONObject || o instanceof JSONArray) {
            return callback + "(" + o + ")";
        } else {
            return callback + "('" + JSON.toJSONString(o) + "')";
        }
    }


    public static String success_front(Object o) {
        JSONObject object = new JSONObject();
        object.put("code", 1);
        object.put("state", "success");
        object.put("errormsg", "");
        if (null != o) {
            if (o instanceof JSONObject || o instanceof JSONArray) {
                object.put("data", o);
            }
//            else if(o instanceof  List){
//                JSONArray array = JSON.parseArray(JSON.toJSONString(o));
//            }
            else {
                object.put("data", o.toString());
            }
        } else {
            object.put("data", null);
        }
        return object.toString();
    }


    public static ModelAndView pageFail(String errormsg) {
        return new ModelAndView("/other/import_fail").addObject("data", errormsg);
    }


    public static void main(String [] args){

        System.out.println(success("abc"));
        System.out.println(success(123));

    }


}
