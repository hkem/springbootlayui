package com.example.demo.entity;

public class Admin {
    public int admin_id;
    public String admin_img;
    public String admin_name;
    public String created_at;
    public String updated_at;
    public String admin_pass;
    public String admin_number;
    public int is_delete;
    public int is_default;
    public int is_show;

    //分页
    public int pages;
    public int pagecount;
    public int listcount;

    public String admin_idstr;

    public String getAdmin_idstr() {
        return admin_idstr;
    }

    public void setAdmin_idstr(String admin_idstr) {
        this.admin_idstr = admin_idstr;
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

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

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

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}
