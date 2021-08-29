package com.example.demo.service;

import com.example.demo.entity.AttestationAdmin;
import com.example.demo.mapper.AttestationAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttestationAdminService {

    @Autowired
    private AttestationAdminMapper attestationAdminMapper;

    //查询一条
    public List<AttestationAdmin> oneattestationadmin(AttestationAdmin attestationAdmin){
        return attestationAdminMapper.oneattestationadmin(attestationAdmin);
    }

    //添加
    public int insertattestationadmin(AttestationAdmin attestationAdmin){
        return attestationAdminMapper.insertattestationadmin(attestationAdmin);
    }

    //查询全部
    public List<AttestationAdmin> allattestationadmin(AttestationAdmin attestationAdmin){
        return attestationAdminMapper.allattestationadmin(attestationAdmin);
    }

    //更新查询
    public List<AttestationAdmin> oneupdateattestationadmin(AttestationAdmin attestationAdmin){
        return attestationAdminMapper.oneupdateattestationadmin(attestationAdmin);
    }

    //更新
    public int updateattestationadmin(AttestationAdmin attestationAdmin){
        return  attestationAdminMapper.updateattestationadmin(attestationAdmin);
    }

    //删除
    public int deleteattestationadmin(AttestationAdmin attestationAdmin){
        return  attestationAdminMapper.deleteattestationadmin(attestationAdmin);
    }

}
