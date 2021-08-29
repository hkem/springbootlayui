package com.example.demo.Admin.Controller;


import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    //后台登录
    @RequestMapping("/admin/loginpost")
    public Map loginpost(HttpServletRequest request){
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        System.out.println("username:"+username);
        System.out.println("pass:"+pass);
        //存入sessine
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");

        Map data = new HashMap();
        return data;
    }





}
