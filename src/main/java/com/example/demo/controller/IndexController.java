package com.example.demo.controller;


import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexController extends CommonController{

    @Autowired
    private AttestationService attestationService;

    @Autowired
    private AttestationAdminService attestationAdminService;

    @Autowired
    private AttestationGroupService attestationGroupService;

    //主页
    @RequestMapping("/admin/index/index")
    public String index(HttpServletRequest request, Model model){
        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);
        return "admin/index";
    }

    //登录页
    @RequestMapping("/admin/login")
    public String login(){
        return  "admin/login";
    }

    //无权限页
    @RequestMapping("/power")
    public String power(){
        return  "admin/public/power";
    }

}
