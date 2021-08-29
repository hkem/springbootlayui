package com.example.demo.entity;

import java.util.List;

public class Attestation {

    public int attestation_id;
    public int parent_id;
    public String attestation_url;
    public String attestation_name;
    public int is_menu;
    public int is_authentication;
    public String attestation_img;
    public String created_at;
    public String updated_at;
    public int sort;
    public int request_type;

    //二级数据
    public List<Attestation> parent2;

    //三级数据
    public List<Attestation> parent3;

    //分页
    public int pages;
    public int pagecount;
    public int listcount;

    public String attestation_idstr;
    public int existence; //0 不存在 1 存在


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

    public String getAttestation_idstr() {
        return attestation_idstr;
    }

    public void setAttestation_idstr(String attestation_idstr) {
        this.attestation_idstr = attestation_idstr;
    }

    public int getExistence() {
        return existence;
    }

    public void setExistence(int existence) {
        this.existence = existence;
    }

    public List<Attestation> getParent2() {
        return parent2;
    }

    public void setParent2(List<Attestation> parent2) {
        this.parent2 = parent2;
    }

    public List<Attestation> getParent3() {
        return parent3;
    }

    public void setParent3(List<Attestation> parent3) {
        this.parent3 = parent3;
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

    public String getAttestation_img() {
        return attestation_img;
    }

    public void setAttestation_img(String attestation_img) {
        this.attestation_img = attestation_img;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getListcount() {
        return listcount;
    }

    public void setListcount(int listcount) {
        this.listcount = listcount;
    }
}
