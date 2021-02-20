package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AOP.PassToken;
import com.example.demo.Util.JwtUtils;

@RestController
public class Controller {

	@PassToken
    @GetMapping(value = "/login")
    public String login(String userName, String password) throws Exception{

		/*
        try{
            service.login(userName,password);
        }
        catch (AuthenticationException e)
        {
            throw new Exception();
        }
        */

        //如果成功了，聚合需要返回的信息
        // String account = accountService.getAccountByUserName(userName);

        //给分配一个token 然后返回
        String jwtToken = JwtUtils.createToken(userName, userName, userName);

        return jwtToken;

    }

	@GetMapping(value = "/username")
    public String checkName(HttpServletRequest req) {
        //之前在拦截器里设置好的名字现在可以取出来直接用了
        String name = (String) req.getAttribute("userName");
        return name;
    }
}
