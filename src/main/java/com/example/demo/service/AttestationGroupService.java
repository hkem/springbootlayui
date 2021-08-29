package com.example.demo.service;

import com.example.demo.entity.AttestationGroup;
import com.example.demo.mapper.AttestationGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttestationGroupService {

    @Autowired
    private AttestationGroupMapper attestationGroupMapper;


    //单条
    public List<AttestationGroup> oneattestationgroup(AttestationGroup attestationGroup){
        return attestationGroupMapper.oneattestationgroup(attestationGroup);
    }

    //添加
    public int insertattestationaroup(AttestationGroup attestationGroup){
        return attestationGroupMapper.insertattestationgroup(attestationGroup);
    }

    //查询全部
    public List<AttestationGroup> allattestationgroup(AttestationGroup attestationGroup){
        return attestationGroupMapper.allattestationgroup(attestationGroup);
    }

    //更新查询
    public List<AttestationGroup> oneupdateattestationgroup(AttestationGroup attestationGroup){
        return attestationGroupMapper.oneupdateattestationgroup(attestationGroup);
    }

    //更新
    public int updateattestationgroup(AttestationGroup attestationGroup){
        return attestationGroupMapper.updateattestationgroup(attestationGroup);
    }

    //删除
    public int deleteattestationgroup(AttestationGroup attestationGroup){
        return  attestationGroupMapper.deleteattestationgroup(attestationGroup);
    }
}
