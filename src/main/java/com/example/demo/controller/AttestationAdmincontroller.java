package com.example.demo.controller;

import com.example.demo.common.ReturnJson;
import com.example.demo.entity.Admin;
import com.example.demo.entity.AttestationAdmin;
import com.example.demo.entity.AttestationGroup;
import com.example.demo.service.AdminService;
import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;
import com.example.demo.validator.AttestationAdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AttestationAdmincontroller {

    SimpleDateFormat silptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //返回
    private ReturnJson returnJson;

    @Autowired
    private AttestationGroupService attestationGroupService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private AttestationService attestationService;

    @Autowired
    private AttestationAdminService attestationAdminService;

    //列表
    @RequestMapping("/admin/attestationadmin/index")
    public String index(HttpServletRequest request, Model model){

        AttestationAdmin attestationAdmin = new AttestationAdmin();
        List<AttestationAdmin> list = attestationAdminService.allattestationadmin(attestationAdmin);

        Admin admin = new Admin();
        AttestationGroup attestationGroup = new AttestationGroup();
        for (int a=0;a<list.size();a++){
            //获取管理员
            admin.setAdmin_id(list.get(a).getAdmin_id());
            List<Admin> adminlist = adminService.oneselectadmin(admin);
            list.get(a).setAdmin_name(adminlist.get(0).getAdmin_name());
            //获取分组
            attestationGroup.setAttestation_group_id(list.get(a).getAttestation_group_id());
            List<AttestationGroup> groupslist = attestationGroupService.oneattestationgroup(attestationGroup);
            list.get(a).setGroup_name(groupslist.get(0).getGroup_name());
        }
        model.addAttribute("attestationadminlist",list);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/attestationadmin/index";
    }

    //添加页面
    @RequestMapping("/admin/attestationadmin/insertview")
    public String insertview(HttpServletRequest request,Model model){

        //获取全部管理员
        Admin admin = new Admin();
        List<Admin> adminlist = adminService.getadmin(admin);
        //获取全部权限
        AttestationGroup attestationGroup = new AttestationGroup();
        List<AttestationGroup> groups = attestationGroupService.allattestationgroup(attestationGroup);

        model.addAttribute("adminlist",adminlist);
        model.addAttribute("groups",groups);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/attestationadmin/insertview";
    }


    //添加
    @RequestMapping("/admin/attestationadmin/attestationadmininsertpost")
    @ResponseBody
    public Map attestationadmininsertpost(@Validated @RequestBody AttestationAdminValidator attestationAdminValidator, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return returnJson.returndata(0, bindingResult.getFieldError().getDefaultMessage(), new HashMap());
        }
        AttestationAdmin attestationAdmin = new AttestationAdmin();
        //判断是否存在
        attestationAdmin.setAdmin_id(attestationAdminValidator.getAdmin_id());
        List<AttestationAdmin> onelistadmin = attestationAdminService.oneattestationadmin(attestationAdmin);
        if (onelistadmin.size() > 0) {
            return returnJson.returndata(0, "管理员已存在", new HashMap());
        }

        String time = silptime.format(new Date());
        AttestationAdmin attestationAdmin1 = new AttestationAdmin();
        attestationAdmin1.setAdmin_id(attestationAdminValidator.getAdmin_id());
        attestationAdmin1.setAttestation_group_id(attestationAdminValidator.getAttestation_group_id());
        attestationAdmin1.setCreated_at(time);
        attestationAdmin1.setUpdated_at(time);
        int suee = attestationAdminService.insertattestationadmin(attestationAdmin1);
        if(suee > 0){
            return returnJson.returndata(1,"添加成功",new HashMap());
        }else{
            return returnJson.returndata(0,"添加失败",new HashMap());
        }
    }
    //修改页面
    @RequestMapping("/admin/attestationadmin/updateview")
    public String updateview(HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        AttestationAdmin attestationAdmin = new AttestationAdmin();
        attestationAdmin.setAttestation_admin_id(Integer.parseInt(id));
        List<AttestationAdmin> list = attestationAdminService.oneattestationadmin(attestationAdmin);
        if(list.size() <= 0){
            model.addAttribute("error_str","id错误，数据不存在");
            return "admin/public/error";
        }
        model.addAttribute("attestationadminlist",list.get(0));
        //获取全部管理员
        Admin admin = new Admin();
        List<Admin> adminlist = adminService.getadmin(admin);
        //获取全部权限
        AttestationGroup attestationGroup = new AttestationGroup();
        List<AttestationGroup> groups = attestationGroupService.allattestationgroup(attestationGroup);

        model.addAttribute("adminlist",adminlist);
        model.addAttribute("groups",groups);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/attestationadmin/updateview";
    }

    //修改
    @RequestMapping("/admin/attestationadmin/attestationadminupdatepost")
    @ResponseBody
    public Map attestationadminupdatepost(@Validated @RequestBody AttestationAdminValidator attestationAdminValidator, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return returnJson.returndata(0, bindingResult.getFieldError().getDefaultMessage(), new HashMap());
        }
        AttestationAdmin attestationAdmin = new AttestationAdmin();
        //判断是否存在
        attestationAdmin.setAdmin_id(attestationAdminValidator.getAdmin_id());
        attestationAdmin.setAttestation_admin_id(attestationAdminValidator.getAttestation_admin_id());
        List<AttestationAdmin> onelistadmin = attestationAdminService.oneupdateattestationadmin(attestationAdmin);
        if (onelistadmin.size() > 0) {
            return returnJson.returndata(0, "管理员已存在", new HashMap());
        }
        String time = silptime.format(new Date());
        AttestationAdmin attestationAdmin1 = new AttestationAdmin();
        attestationAdmin1.setAttestation_admin_id(attestationAdminValidator.getAttestation_admin_id());
        attestationAdmin1.setAdmin_id(attestationAdminValidator.getAdmin_id());
        attestationAdmin1.setAttestation_group_id(attestationAdminValidator.getAttestation_group_id());
        attestationAdmin1.setUpdated_at(time);
        int suee = attestationAdminService.updateattestationadmin(attestationAdmin1);
        if(suee > 0){
            return returnJson.returndata(1,"修改成功",new HashMap());
        }else{
            return returnJson.returndata(0,"修改失败",new HashMap());
        }
    }
    //删除
    @RequestMapping("/admin/attestationadmin/deleteattestationadmin")
    @ResponseBody
    public Map deleteattestationadmin(HttpServletRequest request){
        String id = request.getParameter("id");
        AttestationAdmin attestationAdmin = new AttestationAdmin();
        attestationAdmin.setAttestation_admin_idstr(id);
        int suee = attestationAdminService.deleteattestationadmin(attestationAdmin);
        if(suee > 0){
            return returnJson.returndata(1,"删除成功",new HashMap());
        }else{
            return returnJson.returndata(0,"删除失败",new HashMap());
        }
    }



}
