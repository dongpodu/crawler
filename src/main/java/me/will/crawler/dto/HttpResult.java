package me.will.crawler.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/5/24.
 * 包装json
 */
@Data
public class HttpResult<T> implements Serializable {
    /**
     * 保持会话的token
     */
    private String token;
    private T data;
    private int status=200;
    private String errMsg;

    public static HttpResult success(){
        HttpResult httpResult = new HttpResult();
        return httpResult;
    }

    public static <T> HttpResult success(T data){
        HttpResult httpResult = new HttpResult();
        httpResult.setData(data);
        return httpResult;
    }

    public static <T> HttpResult success(String key,T value){
        HttpResult httpResult = new HttpResult();
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        httpResult.setData(map);
        return httpResult;
    }

    public static HttpResult fail(int status, String resultMsg){
        HttpResult httpResult = new HttpResult();
        httpResult.setStatus(status);
        httpResult.setErrMsg(resultMsg);
        return httpResult;
    }
}
