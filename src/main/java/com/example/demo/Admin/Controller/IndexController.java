package com.example.demo.Admin.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController extends CommonController{


    //主页
    @RequestMapping("/admin/index")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object string = session.getAttribute("hello");
        System.out.println(string);
        return "admin/index";
    }

    //登录页
    @RequestMapping("/admin/login")
    public String login(){
        return  "admin/login";
    }




}
