package com.example.demo.controller;

import com.example.demo.common.ReturnJson;
import com.example.demo.entity.Attestation;
import com.example.demo.entity.AttestationAdmin;
import com.example.demo.entity.AttestationGroup;
import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;
import com.example.demo.validator.AttestationGroupValidator;
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
public class AttestationGroupController {

    SimpleDateFormat silptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //返回
    private ReturnJson returnJson;

    @Autowired
    private AttestationService attestationService;

    @Autowired
    private AttestationGroupService attestationGroupService;

    @Autowired
    private AttestationAdminService attestationAdminService;

    //列表
    @RequestMapping("/admin/attestationgroup/index")
    public String index(HttpServletRequest request, Model model){

        AttestationGroup attestationGroup = new AttestationGroup();
        List<AttestationGroup> list = attestationGroupService.allattestationgroup(attestationGroup);
        model.addAttribute("attestationgrouplist",list);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);

        return "admin/attestationgroup/index";
    }


    //添加页面
    @RequestMapping("/admin/attestationgroup/insertview")
    public String insertview(HttpServletRequest request,Model model){

        Attestation attestation = new Attestation();
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
        model.addAttribute("attestationlist",list);


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);

        return "admin/attestationgroup/insertview";
    }

    //添加提交
    @RequestMapping("/admin/attestationgroup/attestationgroupinsertpost")
    @ResponseBody
    public Map attestationgroupinsertpost(@Validated @RequestBody AttestationGroupValidator attestationAroupValidator, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }
        AttestationGroup attestationAroup = new AttestationGroup();
        //判断名称
        attestationAroup.setGroup_name(attestationAroupValidator.getGroup_name());
        List<AttestationGroup> onelistname = attestationGroupService.oneattestationgroup(attestationAroup);
        if(onelistname.size() > 0){
            return returnJson.returndata(0,"名称已存在",new HashMap());
        }


        String time = silptime.format(new Date());
        AttestationGroup attestationAroup1 = new AttestationGroup();
        attestationAroup1.setGroup_name(attestationAroupValidator.getGroup_name());
        attestationAroup1.setAttestation_id(attestationAroupValidator.getAttestation_id());
        attestationAroup1.setCreated_at(time);
        attestationAroup1.setUpdated_at(time);
        int suee = attestationGroupService.insertattestationaroup(attestationAroup1);
        if(suee > 0){
            return returnJson.returndata(1,"添加成功",new HashMap());
        }else{
            return returnJson.returndata(0,"添加失败",new HashMap());
        }
    }

    public static boolean useLoop(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }

    //修改页面
    @RequestMapping("/admin/attestationgroup/updateview")
    public String updateview(HttpServletRequest request,Model model){

        String id = request.getParameter("id");
        AttestationGroup attestationGroup = new AttestationGroup();
        attestationGroup.setAttestation_group_id(Integer.parseInt(id));
        List<AttestationGroup> attestationgroup = attestationGroupService.oneattestationgroup(attestationGroup);
        if(attestationgroup.size() <= 0){
            model.addAttribute("error_str","id错误，数据不存在");
            return "admin/public/error";
        }
        String attestation_id = attestationgroup.get(0).getAttestation_id();
        String[] attestation_idarr=attestation_id.split(",");


        Attestation attestation = new Attestation();
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

                for(int c=0;c<list3.size();c++){

                    //判断是否存在
                    boolean cunc = useLoop(attestation_idarr,(list3.get(c).getAttestation_id()+""));
                    if(cunc){
                        list3.get(c).setExistence(1);
                    }else{
                        list3.get(c).setExistence(0);
                    }
                }
                //判断是否存在
                boolean cunb = useLoop(attestation_idarr,(list2.get(b).getAttestation_id()+""));
                if(cunb){
                    list2.get(b).setExistence(1);
                }else{
                    list2.get(b).setExistence(0);
                }

                list2.get(b).setParent3(list3);
            }

            //判断是否存在
            boolean cuna = useLoop(attestation_idarr,(list.get(a).getAttestation_id()+""));
            if(cuna){
                list.get(a).setExistence(1);
            }else{
                list.get(a).setExistence(0);
            }

            list.get(a).setParent2(list2);
        }
        model.addAttribute("attestationlist",list);
        model.addAttribute("attestationgroup",attestationgroup.get(0));


        CommonController commonController = new CommonController();
        commonController.menucomm(request,model);

        return "admin/attestationgroup/updateview";
    }

    //修改提交
    @RequestMapping("/admin/attestationgroup/attestationgroupupdatepost")
    @ResponseBody
    public Map attestationgroupupdatepost(@Validated @RequestBody AttestationGroupValidator attestationAroupValidator, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return returnJson.returndata(0,bindingResult.getFieldError().getDefaultMessage(),new HashMap());
        }
        AttestationGroup attestationAroup = new AttestationGroup();
        //判断名称
        attestationAroup.setGroup_name(attestationAroupValidator.getGroup_name());
        attestationAroup.setAttestation_group_id(attestationAroupValidator.getAttestation_group_id());
        List<AttestationGroup> onelistname = attestationGroupService.oneupdateattestationgroup(attestationAroup);
        if(onelistname.size() > 0){
            return returnJson.returndata(0,"名称已存在",new HashMap());
        }
        String time = silptime.format(new Date());
        AttestationGroup attestationAroup1 = new AttestationGroup();
        attestationAroup1.setAttestation_group_id(attestationAroupValidator.getAttestation_group_id());
        attestationAroup1.setGroup_name(attestationAroupValidator.getGroup_name());
        attestationAroup1.setAttestation_id(attestationAroupValidator.getAttestation_id());
        attestationAroup1.setUpdated_at(time);
        int suee = attestationGroupService.updateattestationgroup(attestationAroup1);
        if(suee > 0){
            return returnJson.returndata(1,"更新成功",new HashMap());
        }else{
            return returnJson.returndata(0,"更新失败",new HashMap());
        }
    }


    //删除
    @RequestMapping("/admin/attestationgroup/deleteattestationgroup")
    @ResponseBody
    public Map deleteattestation(HttpServletRequest request){
        String id = request.getParameter("id");
        //判断是否有下级
        AttestationAdmin attestationAdmin = new AttestationAdmin();
        attestationAdmin.setAttestation_group_id(Integer.parseInt(id));
        List<AttestationAdmin> list = attestationAdminService.oneattestationadmin(attestationAdmin);
        if(list.size() > 0){
            return returnJson.returndata(0,"存在下级，删除失败",new HashMap());
        }
        AttestationGroup attestationGroup1 = new AttestationGroup();
        attestationGroup1.setAttestation_group_idstr(id);
        int suee = attestationGroupService.deleteattestationgroup(attestationGroup1);
        if(suee > 0){
            return returnJson.returndata(1,"删除成功",new HashMap());
        }else{
            return returnJson.returndata(0,"删除失败",new HashMap());
        }
    }

}
