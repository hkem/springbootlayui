package com.example.demo.validator;

import javax.validation.constraints.NotNull;

public class AttestationValidator {

    public int attestation_id;
    @NotNull(message = "父级id不能为空")
    public int parent_id;
    @NotNull(message = "地址不能为空")
    public String attestation_url;
    @NotNull(message = "名称不能为空")
    public String attestation_name;
    @NotNull(message = "是否为菜单不能为空")
    public int is_menu;
    @NotNull(message = "是否鉴权不能为空")
    public int is_authentication;
//    @NotNull(message = "头像不能为空")
//    public String attestation_img;
    @NotNull(message = "排序不能为空")
    public int sort;

    public int request_type;


    public int getRequest_type() {
        return request_type;
    }

    public void setRequest_type(int request_type) {
        this.request_type = request_type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getAttestation_id() {
        return attestation_id;
    }

    public void setAttestation_id(int attestation_id) {
        this.attestation_id = attestation_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getAttestation_url() {
        return attestation_url;
    }

    public void setAttestation_url(String attestation_url) {
        this.attestation_url = attestation_url;
    }

    public String getAttestation_name() {
        return attestation_name;
    }

    public void setAttestation_name(String attestation_name) {
        this.attestation_name = attestation_name;
    }

    public int getIs_menu() {
        return is_menu;
    }

    public void setIs_menu(int is_menu) {
        this.is_menu = is_menu;
    }

    public int getIs_authentication() {
        return is_authentication;
    }

    public void setIs_authentication(int is_authentication) {
        this.is_authentication = is_authentication;
    }

//    public String getAttestation_img() {
//        return attestation_img;
//    }
//
//    public void setAttestation_img(String attestation_img) {
//        this.attestation_img = attestation_img;
//    }

}
