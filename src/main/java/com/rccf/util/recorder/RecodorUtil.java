package com.rccf.util.recorder;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

public class RecodorUtil {

    private static final String APP_ID = "10462661";

    private static final String API_KEY = "fdTWCyGGxwk4d4G5Zhj4GZgE";

    private static final String SECRET_KEY = "raAV5n2VsCxDO2azy1iupdUzvybMdbtl";


    public static void main(String args[]){

        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject res = client.asr("../test.pcm", "pcm", 16000, null);
        System.out.println(res.toString(2));
    }




}
