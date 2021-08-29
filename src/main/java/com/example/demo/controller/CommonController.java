package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Attestation;
import com.example.demo.entity.AttestationAdmin;
import com.example.demo.entity.AttestationGroup;
import com.example.demo.service.AttestationAdminService;
import com.example.demo.service.AttestationGroupService;
import com.example.demo.service.AttestationService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonController {




    @Autowired
    AttestationService attestationService;

    @Autowired
    AttestationAdminService attestationAdminService;

    @Autowired
    AttestationGroupService attestationGroupService;

    public static boolean useLoop(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }

    public void menucomm(HttpServletRequest request, Model model){
        if (attestationService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            attestationService = (AttestationService) factory.getBean("attestationService");
        }

        if (attestationAdminService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            attestationAdminService = (AttestationAdminService) factory.getBean("attestationAdminService");
        }

        if (attestationGroupService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            attestationGroupService = (AttestationGroupService) factory.getBean("attestationGroupService");
        }

        String menulist1_idstr = request.getParameter("menulist1_id");

        //获取session查询当前管理员
        Object session = request.getSession().getAttribute("adminsession");
        Map admindatamap = JSONObject.parseObject(JSONObject.toJSONString(session), Map.class);


        String admin_id = admindatamap.get("admin_id").toString();
        int admin_idint = Integer.parseInt(admin_id);

        List<Attestation> newlist1 = new ArrayList<>();
        List<Attestation> newlist2 = new ArrayList<>();

        if(admin_idint != 1){
            //根据当前权限渲染菜单
            AttestationAdmin attestationAdmin = new AttestationAdmin();
            attestationAdmin.setAdmin_id(admin_idint);
            List<AttestationAdmin> attestationAdmins = attestationAdminService.oneattestationadmin(attestationAdmin);
            if(attestationAdmins.size() > 0){
                //存在权限 查看权限
                AttestationGroup attestationGroup = new AttestationGroup();
                attestationGroup.setAttestation_group_id(attestationAdmins.get(0).getAttestation_group_id());
                List<AttestationGroup> attestationGroups= attestationGroupService.oneattestationgroup(attestationGroup);
                String attestation_id = attestationGroups.get(0).getAttestation_id();

                String[] attestation_idstr = attestation_id.split(",");

                //查出所有一级菜单
                Attestation attestation1 = new Attestation();
                attestation1.setParent_id(0);
                attestation1.setIs_menu(1);
                List<Attestation> menulist1 = attestationService.allattestation(attestation1);
                //循环判断是否存在权限数组
                for (int a=0;a<menulist1.size();a++){
                    if(admin_idint != 1){
                        if (useLoop(attestation_idstr,menulist1.get(a).getAttestation_id()+"")){
                            newlist1.add(menulist1.get(a));
                        }
                    }else{
                        newlist1.add(menulist1.get(a));
                    }

                }

                int menulist1_id;
                if(menulist1_idstr == null){
                    menulist1_id = menulist1.get(0).getAttestation_id();
                }else{
                    menulist1_id = Integer.parseInt(menulist1_idstr);
                }

                //查询当前一级菜单中的第一个的所有二级三级的菜单
                //查2级
                Attestation attestation2 = new Attestation();
                attestation2.setParent_id(menulist1_id);
                attestation2.setIs_menu(1);
                List<Attestation> menulist2 = attestationService.allattestation(attestation2);
                for (int b =0;b<menulist2.size();b++){
                    //查3级
                    if (useLoop(attestation_idstr,menulist2.get(b).getAttestation_id()+"")){
                        Attestation attestation3 = new Attestation();
                        attestation3.setParent_id(menulist2.get(b).getAttestation_id());
                        attestation3.setIs_menu(1);
                        List<Attestation> list3 = attestationService.allattestation(attestation3);

                        List<Attestation> newlist3 = new ArrayList<>();

                        for (int c=0;c<list3.size();c++){
                            if (useLoop(attestation_idstr,list3.get(c).getAttestation_id()+"")){
                                newlist3.add(list3.get(c));
                            }
                        }
                        menulist2.get(b).setParent3(newlist3);
                        newlist2.add(menulist2.get(b));
                    }
                }
            }
        }else{
            //查出所有一级菜单
            Attestation attestation1 = new Attestation();
            attestation1.setParent_id(0);
            attestation1.setIs_menu(1);
            List<Attestation> menulist1 = attestationService.allattestation(attestation1);
            //循环判断是否存在权限数组
            for (int a=0;a<menulist1.size();a++){
                newlist1.add(menulist1.get(a));
            }

            int menulist1_id;
            if(menulist1_idstr == null){
                menulist1_id = menulist1.get(0).getAttestation_id();
            }else{
                menulist1_id = Integer.parseInt(menulist1_idstr);
            }

            //查询当前一级菜单中的第一个的所有二级三级的菜单
            //查2级
            Attestation attestation2 = new Attestation();
            attestation2.setParent_id(menulist1_id);
            attestation2.setIs_menu(1);
            List<Attestation> menulist2 = attestationService.allattestation(attestation2);
            for (int b =0;b<menulist2.size();b++){
                //查3级
                    Attestation attestation3 = new Attestation();
                    attestation3.setParent_id(menulist2.get(b).getAttestation_id());
                    attestation3.setIs_menu(1);
                    List<Attestation> list3 = attestationService.allattestation(attestation3);
                    List<Attestation> newlist3 = new ArrayList<>();
                    for (int c=0;c<list3.size();c++){
                            newlist3.add(list3.get(c));
                    }
                    menulist2.get(b).setParent3(newlist3);
                    newlist2.add(menulist2.get(b));

            }

        }

        Map menubale = new HashMap();
        //获取
        String menulist1_id = request.getParameter("menulist1_id");
        String menulist2_id = request.getParameter("menulist2_id");
        String menulist3_id = request.getParameter("menulist3_id");

        //第一个
        menubale.put("menulist1_name","");
        menubale.put("menulist2_name","");
        menubale.put("menulist3_name","");
        if(menulist1_id != null){
            Attestation attestation = new Attestation();
            attestation.setAttestation_id(Integer.parseInt(menulist1_id));
            List<Attestation> menulist1 = attestationService.oneattestation(attestation);
            if(menulist1.size() > 0){
                menubale.put("menulist1_name",menulist1.get(0).attestation_name);
            }
        }
        if(menulist2_id != null){
            Attestation attestation = new Attestation();
            attestation.setAttestation_id(Integer.parseInt(menulist2_id));
            List<Attestation> menulist2 = attestationService.oneattestation(attestation);
            if(menulist2.size() > 0){
                menubale.put("menulist2_name",menulist2.get(0).attestation_name);
            }
        }
        if(menulist3_id != null){
            Attestation attestation = new Attestation();
            attestation.setAttestation_id(Integer.parseInt(menulist3_id));
            List<Attestation> menulist3 = attestationService.oneattestation(attestation);
            if(menulist3.size() > 0){
                menubale.put("menulist3_name",menulist3.get(0).attestation_name);
            }
        }
        model.addAttribute("menubale",menubale);
        model.addAttribute("menulist1",newlist1);
        model.addAttribute("menulist2",newlist2);
        model.addAttribute("admindata",admindatamap);


    }


}
