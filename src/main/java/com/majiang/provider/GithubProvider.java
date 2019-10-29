package com.majiang.provider;

import com.alibaba.fastjson.JSON;
import com.majiang.dto.AccessTokenDTO;
import com.majiang.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by MaoChenDong on 2019/10/20.
 */
@Component
public class GithubProvider {
    public String getAccountToken(AccessTokenDTO accessTokenDTO){
        MediaType mediatype =MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediatype, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();

            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            System.out.println("执行GithubProvider。getAccountToken Error");
        }
        return null;

    }



    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //string json对象自动转化Java类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {

        }
        return null;
    }
}
