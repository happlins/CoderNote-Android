package com.example.codernote.util;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 黄林 on 2017/10/18.
 */

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json:charset=utf-8");

    public static void login(String address,Object object,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(200,TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .connectTimeout(300,TimeUnit.SECONDS)
                .build();
        Gson gson = new Gson();
        String strjson = gson.toJson(object);
        RequestBody requestBody = RequestBody.create(JSON,strjson);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void registerUser(String address,Object object,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .connectTimeout(300,TimeUnit.SECONDS)
                .build();
        Gson gson = new Gson();
        String strjson = gson.toJson(object);
        RequestBody requestBody = RequestBody.create(JSON,strjson);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
