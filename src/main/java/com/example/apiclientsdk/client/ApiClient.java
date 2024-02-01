package com.example.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.example.apiclientsdk.model.userr;

import java.util.HashMap;
import java.util.Map;

import static com.example.apiclientsdk.utils.SignUtils.genSign;

// 客户端代码
public class ApiClient {

    private String accessKey;
    private String secretKey;

    private static final String GATEWAY_HOST = "http://localhost:8090";


    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    // 创建一个私有方法，用于构造请求头
    private Map<String, String> getHeaderMap(String body) {
        // 创建一个新的 HashMap 对象
        Map<String, String> hashMap = new HashMap<>();
        // 将 "accessKey" 和其对应的值放入 map 中
        hashMap.put("accessKey", accessKey);
//        // 将 "secretKey" 和其对应的值放入 map 中
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        // 时间戳，System.currentTimeMillis()返回当前毫秒数，除1000变为秒数
        // valueOf转化为字符串
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign",genSign(body,secretKey));
        // 返回构造的请求头 map
        return hashMap;
    }

//    public String getNameByPosts(@RequestBody User user){
//        String json = JSONUtil.toJsonStr(user);
//        String result2 = HttpRequest.post("http://localhost:8123/api/name/User/")
//                .body(json)
//                .execute().body();
//        System.out.println(result2);
//        return result2;
//    }
    public String getNameByPost(userr user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                // 添加前面构造的请求头
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
//        System.out.println(result);
        return result;
    }

}
