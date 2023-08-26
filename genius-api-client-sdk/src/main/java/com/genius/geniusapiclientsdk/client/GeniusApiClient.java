package com.genius.geniusapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.genius.geniusapiclientsdk.utils.SignUtils.genSign;

/**
 * @author dzl
 * @date 2023/7/29 12:34
 */
public class GeniusApiClient {

    public static String GATEWAY_HOST = "http://localhost:8090";
    private String accessKey;

    private String secretKey;

    public GeniusApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void setGatewayHost(String gatewayHost){
        GATEWAY_HOST = gatewayHost;
    }

//    public String getNameByGet(String name){
//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("name",name);
//        if (!accessKey.equals("8080") || !secretKey.equals("abcdefgh")){
//            return "密码错误！！！";
//        }
//        String result = HttpUtil.get("localhost:8123/api/name", hashMap);
//        System.out.println(result);
//        return result;
//    }
//
//    public String getNameByPost(String name){
//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("name",name);
//        if (!accessKey.equals("8080") || !secretKey.equals("abcdefgh")){
//            return "密码错误！！！";
//        }
//        String result = HttpUtil.post("localhost:8123/api/name", hashMap);
//        System.out.println(result);
//        return result;
//    }
//
//    public String getUsernameByPost(User user){
//        String json = JSONUtil.toJsonStr(user);
//        if (!accessKey.equals("8080") || !secretKey.equals("abcdefgh")){
//            return "密码错误！！！";
//        }
//        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/user").body(json).execute();
//        int status = httpResponse.getStatus();
//        System.out.println(status);
//        String res = httpResponse.body();
//        return res;
//    }
//    public String getUsernameByGateway(User user){
//        String json = JSONUtil.toJsonStr(user);
//        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
//                .addHeaders(getHeadMap(json))
//                .body(json)
//                .execute();
//        System.out.println(httpResponse.getStatus());
//        String res = httpResponse.body();
//        System.out.println(res);
//        return res;
//    }
    public String invokeInterface(String params, String url, String method) throws UnsupportedEncodingException {
//        if (params == null){
//
//        }
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .addHeaders(getHeadMap(params, method))
                .body(params)
                .execute();
        int status = httpResponse.getStatus();
        System.out.println(status);
        return httpResponse.body();
        //return JSONUtil.formatJsonStr(httpResponse.body());
    }
    private Map<String,String> getHeadMap(String body,String method){
        if (body == null){
            body = "genius";
        }
        String encode = null;
        try {
            encode = URLEncoder.encode(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> hasMap = new HashMap<>();
        String nonce = RandomUtil.randomNumbers(4);
        String currentTime = String.valueOf(System.currentTimeMillis() / 1000);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("sign",genSign(body,secretKey));
        hashMap.put("accessKey",accessKey);
        hashMap.put("method", method);
        return hashMap;
    }

//    private Map<String,String> getHeadMap(String body){
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("nonce", RandomUtil.randomNumbers(4));
//        hashMap.put("body",body);
//        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
//        hashMap.put("sign",genSign(body,secretKey));
//        hashMap.put("accessKey",accessKey);
//        return hashMap;
//    }
}
