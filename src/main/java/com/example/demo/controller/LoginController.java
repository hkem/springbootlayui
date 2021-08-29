package com.example.demo.controller;


import com.example.demo.common.PassEncryption;
import com.example.demo.common.ReturnJson;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Validated
@Controller
public class LoginController {

    SimpleDateFormat silptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //返回
    private ReturnJson returnJson;

    @Autowired
    private AdminService adminService;

    private PassEncryption passEncryption;

    //后台登录
    @RequestMapping("/admin/loginpost")
    @ResponseBody
    public Map loginpost(HttpServletRequest request){
        String admin_number = request.getParameter("admin_number");
        String admin_pass = request.getParameter("admin_pass");

        if(admin_number == null){
            return returnJson.returndata(0,"账号不能为空",new HashMap());
        }
        if(admin_number == ""){
            return returnJson.returndata(0,"账号不能为空",new HashMap());
        }
        if(admin_pass == null){
            return returnJson.returndata(0,"密码不能为空",new HashMap());
        }
        if(admin_pass == ""){
            return returnJson.returndata(0,"密码不能为空",new HashMap());
        }
        //查询密码
        Admin admin = new Admin();
        admin.setAdmin_number(admin_number);
        List<Admin> adminlist = adminService.loginadmin(admin);
        if(adminlist.size() > 0){
            if(passEncryption.admindecryptpass(admin_pass,adminlist.get(0).getAdmin_pass())){
                //判断账号是否禁用
                if(adminlist.get(0).getIs_show() == 2){
                    return returnJson.returndata(0,"账号已被禁用，联系管理员",new HashMap());
                }
                //存入sessine
                HttpSession session = request.getSession();
                Map adminsession = new HashMap();
                adminsession.put("admin_id",adminlist.get(0).getAdmin_id());
                adminsession.put("admin_name",adminlist.get(0).getAdmin_name());
                session.setAttribute("adminsession",adminsession);
            }else{
                return returnJson.returndata(0,"密码错误",new HashMap());
            }
            return returnJson.returndata(1,"登录成功",new HashMap());
        }else{
            return returnJson.returndata(0,"账号不存在",new HashMap());
        }
    }

    //退出
    @RequestMapping("/admin/loginoutpost")
    @ResponseBody
    public Map loginpost(HttpServletRequest request,HttpSession session){
        session.invalidate();
        return returnJson.returndata(1,"退出成功",new HashMap());
    }















}
