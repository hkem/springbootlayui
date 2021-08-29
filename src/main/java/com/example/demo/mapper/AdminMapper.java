package com.example.demo.mapper;



import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AdminMapper {
    //登录查询
    List<Admin> loginadmin(Admin admin);
    //添加
    int insertadmin(Admin admin);
    //查询单条数据
    List<Admin> oneselectadmin(Admin admin);
    //查询全部数据
    List<Admin> allselectadmin(Admin admin);
    //更新
    int updateadmin(Admin admin);
    //删除
    int deleteadmin(Admin admin);
    //获取总页数
    List<Admin> getlistcount(Admin admin);
    //更新查询
    List<Admin> updateselectadmin(Admin admin);
    //查询除超级管理员外的所有管理员
    List<Admin> getadmin(Admin admin);

}
