package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //登录查询
    public List<Admin> loginadmin(Admin admin){
        return adminMapper.loginadmin(admin);
    }

    //添加
    public int insertadmin(Admin admin){
        return adminMapper.insertadmin(admin);
    }
    //查询单条数据
    public List<Admin> oneselectadmin(Admin admin){
        return adminMapper.oneselectadmin(admin);
    }
    //查询全部数据
    public List<Admin> allselectadmin(Admin admin){
        return adminMapper.allselectadmin(admin);
    }
    //更新
    public int updateadmin(Admin admin){
        System.out.println(admin.getAdmin_img());
        return adminMapper.updateadmin(admin);
    }
    //删除
    public int deleteadmin(Admin admin){
        return adminMapper.deleteadmin(admin);
    }
    //获取总页数
    public List<Admin> getlistcount(Admin admin){
        return adminMapper.getlistcount(admin);
    }
    //更新查询
    public List<Admin> updateselectadmin(Admin admin){
        return adminMapper.updateselectadmin(admin);
    }

    //查询除超级管理员外的所有管理员
    public List<Admin> getadmin(Admin admin){
        return adminMapper.getadmin(admin);
    }


}
