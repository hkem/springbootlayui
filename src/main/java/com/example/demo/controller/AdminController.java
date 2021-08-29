package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.PassEncryption;
import com.example.demo.common.ReturnJson;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Attestation;
import com.example.demo.service.AdminService;
import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;

import com.example.demo.validator.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Validated

public class AdminController {

    SimpleDateFormat silptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //返回
    private ReturnJson returnJson;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AttestationAdminService attestationAdminService;

    @Autowired
    private AttestationService attestationService;

    @Autowired
    private AttestationGroupService attestationGroupService;

    private PassEncryption passEncryption;

    //管理员首页
    @RequestMapping("/admin/admin/index")
    public String index(HttpServletRequest request, Model model){



        String strpage = request.getParameter("page") == null ? "0" : request.getParameter("page");
        String admin_name = request.getParameter("admin_name");
        String admin_number = request.getParameter("admin_number");
        String is_show = request.getParameter("is_show") == null ? "0" : request.getParameter("is_show");
        int page = Integer.parseInt(strpage);

        Admin admin = new Admin();
        admin.setAdmin_name(admin_name);
        admin.setAdmin_number(admin_number);
        admin.setIs_show(Integer.parseInt(is_show));
        admin.setIs_delete(1);
        //获取总页数
        List<Admin> listcc = adminService.getlistcount(admin);
        int listcount = listcc.get(0).getListcount();
        //处理
        int pagecount = 30;
        admin.setPages(page*pagecount);
        admin.setPagecount(pagecount);
        List<Admin> list = adminService.allselectadmin(admin);
        model.addAttribute("list",list);
        model.addAttribute("listcount",listcount);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/admin/index";
    }

    //管理员添加页面
    @RequestMapping("/admin/admin/insertview")
    public String insertview(HttpServletRequest request,Model model){


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);

        return "admin/admin/insertview";
    }

    //管理员添加提交
    @RequestMapping("/admin/admin/admininsertpost")
    @ResponseBody
    public Map admininsertpost(@Validated @RequestBody AdminValidator adminValidator,BindingResult bindingResult,HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }
        if(adminValidator.getAdmin_pass() == ""){
            return returnJson.returndata(0,"密码不能为空",new HashMap());
        }
        //判断账号是否存在
        Admin oadmin = new Admin();
        oadmin.setAdmin_number(adminValidator.getAdmin_number());
        List<Admin> onelist = adminService.oneselectadmin(oadmin);
        if(onelist.size() > 0){
            return returnJson.returndata(0,"该账号已存在",new HashMap());
        }

        String time = silptime.format(new Date());
        Admin admin = new Admin();
        admin.setAdmin_img(adminValidator.getAdmin_img());
        admin.setAdmin_name(adminValidator.getAdmin_name());

        //加密
        String encodepass = passEncryption.adminencryptionpass(adminValidator.getAdmin_pass());
        admin.setAdmin_pass(encodepass);
        admin.setAdmin_number(adminValidator.getAdmin_number());
        admin.setIs_show(Integer.parseInt(adminValidator.getIs_show()));

        admin.setCreated_at(time);
        admin.setUpdated_at(time);
        int suee = adminService.insertadmin(admin);
        if(suee == 1){
            return returnJson.returndata(1,"添加成功",new HashMap());
        }
        return returnJson.returndata(0,"添加失败",new HashMap());
    }

    //禁用
    @RequestMapping("/admin/admin/updateisshow")
    @ResponseBody
    public Map updateisshow(HttpServletRequest request){
        String id = request.getParameter("id");
        String switchbool = request.getParameter("switchbool");
        int is_show = Boolean.parseBoolean(switchbool) ? 1 : 2;
        String time = silptime.format(new Date());
        Admin admin = new Admin();
        admin.setAdmin_id(Integer.parseInt(id));
        admin.setIs_show(is_show);
        admin.setUpdated_at(time);
        int suee = adminService.updateadmin(admin);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }

    //删除
    @RequestMapping("/admin/admin/deleteadmin")
    @ResponseBody
    public Map deleteadmin(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        String time = silptime.format(new Date());
        Admin admin = new Admin();
        admin.setAdmin_idstr(id);
        admin.setUpdated_at(time);
        admin.setIs_delete(2);
        int suee = adminService.deleteadmin(admin);
        if(suee > 0){
            return returnJson.returndata(1,"删除成功",new HashMap());
        }else{
            return returnJson.returndata(0,"删除失败",new HashMap());
        }
    }

    //修改页面
    @RequestMapping("/admin/admin/updateview")
    public String updateview(HttpServletRequest request,Model model){

        String id = request.getParameter("id");
        Admin admin = new Admin();
        admin.setAdmin_id(Integer.parseInt(id));
        List<Admin> list= adminService.oneselectadmin(admin);
        if(list.size() <= 0){
            model.addAttribute("error_str","id错误，数据不存在");
            return "admin/public/error";
        }
        model.addAttribute("list",list.get(0));


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/admin/updateview";
    }

    //修改提交
    @RequestMapping("/admin/admin/adminupdatepost")
    @ResponseBody()
    public Map adminupdatepost(@Validated @RequestBody AdminValidator adminValidator,BindingResult bindingResult,HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }

        int id = adminValidator.getAdmin_id();
        System.out.println(id);
        //判断账号是否存在
        Admin oadmin = new Admin();
        oadmin.setAdmin_number(adminValidator.getAdmin_number());
        oadmin.setAdmin_id(id);
        List<Admin> onelist = adminService.updateselectadmin(oadmin);
        if(onelist.size() > 0){
            return returnJson.returndata(0,"该账号已存在",new HashMap());
        }

        String time = silptime.format(new Date());
        Admin admin = new Admin();
        admin.setAdmin_id(id);
        admin.setAdmin_img(adminValidator.getAdmin_img());
        admin.setAdmin_name(adminValidator.getAdmin_name());

        //加密
        if(adminValidator.getAdmin_pass() != ""){
            String encodepass = passEncryption.adminencryptionpass(adminValidator.getAdmin_pass());
            admin.setAdmin_pass(encodepass);
        }

        admin.setAdmin_number(adminValidator.getAdmin_number());
        admin.setIs_show(Integer.parseInt(adminValidator.getIs_show()));
        admin.setUpdated_at(time);
        int suee = adminService.updateadmin(admin);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }



}
