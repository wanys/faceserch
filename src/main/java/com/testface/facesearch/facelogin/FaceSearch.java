package com.testface.facesearch.facelogin;

import com.testface.facesearch.utils.GsonUtils;
import com.testface.facesearch.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FaceSearch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String search(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image);
            map1.put("liveness_control", "NORMAL");
            map1.put("group_id_list", "test");
            map1.put("image_type", "BASE64");
            map1.put("quality_control", "LOW");
            
            String param = GsonUtils.toJson(map1);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            AuthService authService=new AuthService();
            String accessToken = authService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject objectResult = new JSONObject(result);
            int error_code=objectResult.getInt("error_code");
            Double score=0.0;
            if(error_code==0){

                JSONObject jsonResult = objectResult.getJSONObject("result");
                JSONArray arrayUserList = jsonResult.getJSONArray("user_list");
                JSONObject scoreInfo =arrayUserList.getJSONObject(0);
                score =scoreInfo.getDouble("score");
            }else {
                String error_msg=objectResult.getString("error_msg");
                return error_msg;
            }
            return score.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public static void main(String[] args) {
        facematch.detect();
    }*/
}

