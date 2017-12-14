package com.rccf.util.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.service.BaseService;

import java.util.List;

public class Page {

    private static final int EVERYPAGE_COUNT = 10;


    /**
     * 分页查询数据
     *
     * @param baseService
     * @param total
     * @param info
     * @return
     */
    public static String limit(BaseService baseService, String total, String info) {
        int count = baseService.getCount(total);
        List list = baseService.queryBySql(info);
        String str = JSON.toJSONString(list);
        JSONArray array = JSON.parseArray(str);
        JSONObject object = new JSONObject();
        object.put("total", count);
//        if(every>0){
//            object.put("every",every);
//        }else {
        object.put("epage", EVERYPAGE_COUNT);
//        }
        object.put("data", array);
        return object.toString();
    }


    /**
     * 分页查询formatclass
     *
     * @param baseService
     * @param total
     * @param info
     * @param clazz
     * @return
     */
    public static String limit(BaseService baseService, String total, String info, Class clazz) {
        int count = baseService.getCount(total);
        List list = baseService.queryBySqlFormatClass(clazz, info);
        String str = JSON.toJSONString(list);
        JSONArray array = JSON.parseArray(str);
        JSONObject object = new JSONObject();
        object.put("total", count);
//        if(every>0){
//            object.put("every",every);
//        }else {
        object.put("epage", EVERYPAGE_COUNT);
//        }
        object.put("data", array);
        return object.toString();
    }


}
