package com.majiang.controller;

import com.majiang.dto.AccessTokenDTO;
import com.majiang.dto.GithubUser;

import com.majiang.pojo.User;
import com.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by MaoChenDong on 2019/10/20.
 */
@Controller
public class CallController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${githun.redirect.url}")
    private String redirect_uri;



    //80e16c95f661383c8cb9c3859adb2dc33c7bb2b1
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){
        System.out.println("开始执行 callback");
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        //accessTokenDTO.setClient_secert("bbdd5880206211a94d6d39ccf1df76342d72bd1d");
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        System.out.println("我是 accounutdto#"+accessTokenDTO);
        System.out.println("excute#######+getAccountToken");
        String accessToken = githubProvider.getAccountToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        System.out.println("判断执行前是githubUser.getName否为空"+githubUser.getName());
//        if(githubUser !=null){
//            User user = new User();
//            user.setAccountId(String.valueOf(githubUser.getId()));
//            user.setToken(UUID.randomUUID().toString());
//            user.setName(githubUser.getName());
//            user.setGmtCreate(System.currentTimeMillis());
//            user.setGmtModified(user.getGmtCreate());
//            usermapper.insert(user);
//            System.out.println("插入成功");
//            request.getSession().setAttribute("user",githubUser);
//            return "redirect:/";
//
//        }else{
//            System.out.println("登陆失败");
//            return "redirect:/";
//        }





         return "redirect:/";
    }

}
