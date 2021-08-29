package com.example.demo.Interceptor;

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
import org.springframework.ui.Model;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    AttestationService attestationService;

    @Autowired
    AttestationAdminService attestationAdminService;

    @Autowired
    AttestationGroupService attestationGroupService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");


        //权限这里由于用的是全局权限 所以只能判断数据里面存在的  不存在一律直接通过
        String url = request.getRequestURI().toString();

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

        Object session = null;
        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            session = request.getSession().getAttribute("adminsession");
            if(session!=null){

                Attestation attestation = new Attestation();
                attestation.setAttestation_url(url);
                attestation.setIs_authentication(1);
                List<Attestation> attestations = attestationService.oneattestation(attestation);

                if(attestations.size() > 0) {
                    //存在

                    Map admindatamap = JSONObject.parseObject(JSONObject.toJSONString(session), Map.class);

                    String admin_id = admindatamap.get("admin_id").toString();
                    int admin_idint = Integer.parseInt(admin_id);
                    if (admin_idint != 1) {

                        AttestationAdmin attestationAdmin = new AttestationAdmin();
                        attestationAdmin.setAdmin_id(admin_idint);
                        List<AttestationAdmin> attestationAdmins = attestationAdminService.oneattestationadmin(attestationAdmin);
                        if (attestationAdmins.size() > 0) {

                            //存在权限 查看权限
                            AttestationGroup attestationGroup = new AttestationGroup();
                            attestationGroup.setAttestation_group_id(attestationAdmins.get(0).getAttestation_group_id());
                            List<AttestationGroup> attestationGroups = attestationGroupService.oneattestationgroup(attestationGroup);
                            String attestation_id = attestationGroups.get(0).getAttestation_id();

                            String[] attestation_idstr = attestation_id.split(",");

                            //判断是否有权限
                            if(useLoop(attestation_idstr,attestations.get(0).getAttestation_id()+"")){
                                return true;
                            }else{
                                if(attestations.get(0).getRequest_type() == 1){
                                    response.setCharacterEncoding("UTF-8");
                                    response.setContentType("application/json; charset=utf-8");
                                    PrintWriter out = null;
                                    JSONObject res = new JSONObject();
                                    res.put("code", 0);
                                    res.put("msg", "无权限操作");
                                    res.put("data", new HashMap<>());
                                    out = response.getWriter();
                                    out.append(res.toString());
                                    return false;
                                }else{
                                    //页面
                                    try {
                                        response.sendRedirect(request.getContextPath()+"/power");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }else{
                response.sendRedirect(request.getContextPath()+"/admin/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    public static boolean useLoop(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}
