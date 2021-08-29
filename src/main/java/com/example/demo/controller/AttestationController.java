package com.example.demo.controller;

import com.example.demo.common.ReturnJson;
import com.example.demo.entity.Attestation;
import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;
import com.example.demo.validator.AdminValidator;
import com.example.demo.validator.AttestationValidator;
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
import java.util.*;

@Controller
public class AttestationController {

    SimpleDateFormat silptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //返回
    private ReturnJson returnJson;

    @Autowired
    private AttestationService attestationService;

    @Autowired
    private AttestationAdminService attestationAdminService;

    @Autowired
    private AttestationGroupService attestationGroupService;

    //列表
    @RequestMapping("/admin/attestation/index")
    public String index(HttpServletRequest request, Model model){
        String attestation_url = request.getParameter("attestation_url");
        String attestation_name = request.getParameter("attestation_name");
        Attestation attestation = new Attestation();
        attestation.setAttestation_url(attestation_url);
        attestation.setAttestation_name(attestation_name);
        attestation.setParent_id(0);
        List<Attestation> list = attestationService.allattestation(attestation);
        for (int a=0;a<list.size();a++){
            //查2级
            Attestation attestation2 = new Attestation();
            attestation2.setParent_id(list.get(a).getAttestation_id());
            List<Attestation> list2 = attestationService.allattestation(attestation2);
            for (int b =0;b<list2.size();b++){
                //查3级
                Attestation attestation3 = new Attestation();
                attestation3.setParent_id(list2.get(b).getAttestation_id());
                List<Attestation> list3 = attestationService.allattestation(attestation3);
                list2.get(b).setParent3(list3);
            }
            list.get(a).setParent2(list2);
        }
        model.addAttribute("listaa",list);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);



        return "admin/attestation/index";
    }


    //添加页面
    @RequestMapping("/admin/attestation/insertview")
    public String insertview(HttpServletRequest request,Model model){
        //查询父级
        Attestation attestation = new Attestation();
        attestation.setParent_id(0);
        List<Attestation> parentlist = attestationService.parentlist(attestation);
        for(int x = 0; x < parentlist.size(); x++) {
            //获取二级数据
            Attestation attestation2 = new Attestation();
            attestation2.setParent_id(parentlist.get(x).getAttestation_id());
            List<Attestation> parentlist2 = attestationService.parentlist(attestation2);
            parentlist.get(x).setParent2(parentlist2);
        }
        model.addAttribute("parentlist",parentlist);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);


        return "admin/attestation/insertview";
    }

    //添加提交
    @RequestMapping("/admin/attestation/attestationinsertpost")
    @ResponseBody
    public Map attestationinsertpost(@Validated @RequestBody AttestationValidator attestationValidator, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }

        Attestation attestation1 = new Attestation();
        //判断名称
        attestation1.setAttestation_name(attestationValidator.getAttestation_name());
        List<Attestation> onelistname = attestationService.oneattestation(attestation1);
        if(onelistname.size() > 0){
            return returnJson.returndata(0,"名称已存在",new HashMap());
        }
        //判断url
        Attestation attestation2 = new Attestation();
        attestation2.setAttestation_url(attestationValidator.getAttestation_url());
        List<Attestation> onelisturl = attestationService.oneattestation(attestation2);
        if(onelisturl.size() > 0){
            return returnJson.returndata(0,"地址已存在",new HashMap());
        }
        String time = silptime.format(new Date());
        Attestation attestation = new Attestation();
        attestation.setParent_id(attestationValidator.getParent_id());
        attestation.setAttestation_url(attestationValidator.getAttestation_url());
        attestation.setAttestation_name(attestationValidator.getAttestation_name());
        attestation.setIs_menu(attestationValidator.getIs_menu());
        attestation.setIs_authentication(attestationValidator.getIs_authentication());
        attestation.setSort(attestationValidator.getSort());
        attestation.setCreated_at(time);
        attestation.setUpdated_at(time);
        attestation.setRequest_type(attestationValidator.getRequest_type());
        int suee = attestationService.insertattestation(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"添加成功",new HashMap());
        }else{
            return returnJson.returndata(0,"添加失败",new HashMap());
        }
    }

    //更改菜单
    @RequestMapping("/admin/attestation/updateismenu")
    @ResponseBody
    public Map updateismenu(HttpServletRequest request){
        String id = request.getParameter("id");
        String switchbool = request.getParameter("switchbool");
        int is_menu = Boolean.parseBoolean(switchbool) ? 1 : 2;
        String time = silptime.format(new Date());
        Attestation attestation = new Attestation();
        attestation.setAttestation_id(Integer.parseInt(id));
        attestation.setIs_menu(is_menu);
        attestation.setUpdated_at(time);
        int suee = attestationService.updateismenu(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }

    //更改鉴权
    @RequestMapping("/admin/attestation/updateisauthentication")
    @ResponseBody
    public Map updateisauthentication(HttpServletRequest request){
        String id = request.getParameter("id");
        String switchbool = request.getParameter("switchbool");
        int is_menu = Boolean.parseBoolean(switchbool) ? 1 : 2;
        String time = silptime.format(new Date());
        Attestation attestation = new Attestation();
        attestation.setAttestation_id(Integer.parseInt(id));
        attestation.setIs_authentication(is_menu);
        attestation.setUpdated_at(time);
        int suee = attestationService.updateismenu(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }

    //更改排序
    @RequestMapping("/admin/attestation/updateattestationsort")
    @ResponseBody
    public Map updateattestationsort(HttpServletRequest request){
        String id = request.getParameter("id");
        String sortval = request.getParameter("val");
        String time = silptime.format(new Date());
        Attestation attestation = new Attestation();
        attestation.setAttestation_id(Integer.parseInt(id));
        attestation.setSort(Integer.parseInt(sortval));
        attestation.setUpdated_at(time);
        int suee = attestationService.updatesort(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }

    //更新页面
    @RequestMapping("/admin/attestation/updateview")
    public String updateview(HttpServletRequest request,Model model){
        //查询父级
        Attestation attestation = new Attestation();
        attestation.setParent_id(0);
        List<Attestation> parentlist = attestationService.parentlist(attestation);
        for(int x = 0; x < parentlist.size(); x++) {
            //获取二级数据
            Attestation attestation2 = new Attestation();
            attestation2.setParent_id(parentlist.get(x).getAttestation_id());
            List<Attestation> parentlist2 = attestationService.parentlist(attestation2);
            parentlist.get(x).setParent2(parentlist2);
        }
        model.addAttribute("parentlist",parentlist);

        String id = request.getParameter("id");
        Attestation attestation1 = new Attestation();
        attestation1.setAttestation_id(Integer.parseInt(id));
        List<Attestation> list = attestationService.oneattestation(attestation1);
        if(list.size() <= 0){
            model.addAttribute("error_str","id错误，数据不存在");
            return "admin/public/error";
        }
        model.addAttribute("updatelist",list.get(0));


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);

        return "admin/attestation/updateview";
    }

    //更新提交
    @RequestMapping("/admin/attestation/attestationupdatepost")
    @ResponseBody
    public Map attestationupdatepost(@Validated @RequestBody AttestationValidator attestationValidator, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }

        int id = attestationValidator.getAttestation_id();

        Attestation attestation1 = new Attestation();
        //判断名称
        attestation1.setAttestation_name(attestationValidator.getAttestation_name());
        attestation1.setAttestation_id(id);
        List<Attestation> onelistname = attestationService.oneupdateattestation(attestation1);
        if(onelistname.size() > 0){
            return returnJson.returndata(0,"名称已存在",new HashMap());
        }

        //判断url
        Attestation attestation2 = new Attestation();
        attestation2.setAttestation_url(attestationValidator.getAttestation_url());
        attestation2.setAttestation_id(id);
        List<Attestation> onelisturl = attestationService.oneupdateattestation(attestation2);
        if(onelisturl.size() > 0){
            return returnJson.returndata(0,"地址已存在",new HashMap());
        }
        String time = silptime.format(new Date());
        Attestation attestation = new Attestation();
        attestation.setAttestation_id(id);
        attestation.setParent_id(attestationValidator.getParent_id());
        attestation.setAttestation_url(attestationValidator.getAttestation_url());
        attestation.setAttestation_name(attestationValidator.getAttestation_name());
        attestation.setIs_menu(attestationValidator.getIs_menu());
        attestation.setIs_authentication(attestationValidator.getIs_authentication());
        attestation.setSort(attestationValidator.getSort());
        attestation.setUpdated_at(time);
        attestation.setRequest_type(attestationValidator.getRequest_type());
        int suee = attestationService.updateattestation(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }

    //删除
    @RequestMapping("/admin/attestation/deleteattestation")
    @ResponseBody
    public Map deleteattestation(HttpServletRequest request){
        String id = request.getParameter("id");
        //判断是否有下级
        Attestation attestation1 = new Attestation();
        attestation1.setAttestation_idstr(id);
        List<Attestation> list = attestationService.onedeleteattestation(attestation1);
        if(list.size() > 0){
            return returnJson.returndata(0,"存在下级，删除失败",new HashMap());
        }
        Attestation attestation = new Attestation();
        attestation.setAttestation_idstr(id);
        int suee = attestationService.deleteattestation(attestation);
        if(suee > 0){
            return returnJson.returndata(1,"删除成功",new HashMap());
        }else{
            return returnJson.returndata(0,"删除失败",new HashMap());
        }
    }




}
