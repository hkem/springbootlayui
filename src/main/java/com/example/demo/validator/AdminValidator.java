package com.example.demo.validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AdminValidator {

    private int admin_id;

    @NotNull(message = "头像不能为空")
    private String admin_img;

    @NotBlank(message = "昵称不能为空")
    private String admin_name;

//    @NotBlank(message = "密码不能为空")
    private String admin_pass;

    @NotBlank(message = "账号不能为空")
    private String admin_number;

    @NotBlank(message = "是否禁用不能为空")
    private String is_show;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_img() {
        return admin_img;
    }

    public void setAdmin_img(String admin_img) {
        this.admin_img = admin_img;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_pass() {
        return admin_pass;
    }

    public void setAdmin_pass(String admin_pass) {
        this.admin_pass = admin_pass;
    }

    public String getAdmin_number() {
        return admin_number;
    }

    public void setAdmin_number(String admin_number) {
        this.admin_number = admin_number;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }
}
