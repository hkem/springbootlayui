package com.example.demo.service;

import com.example.demo.entity.Attestation;
import com.example.demo.mapper.AttestationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttestationService {

    @Autowired
    private AttestationMapper attestationMapper;

    //查询父级
    public List<Attestation> parentlist(Attestation attestation){
        return attestationMapper.parentlist(attestation);
    }

    //添加
    public int insertattestation(Attestation attestation){
        return attestationMapper.insertattestation(attestation);
    }

    //查询单条
    public List<Attestation> oneattestation(Attestation attestation){
        return attestationMapper.oneattestation(attestation);
    }

    //查询全部
    public List<Attestation> allattestation(Attestation attestation){
        return attestationMapper.allattestation(attestation);
    }

    //更新鉴权 菜单
    public int updateismenu(Attestation attestation){
        return attestationMapper.updateismenu(attestation);
    }

    //更改排序
    public int updatesort(Attestation attestation){
        return attestationMapper.updatesort(attestation);
    }

    //更新查询
    public List<Attestation> oneupdateattestation(Attestation attestation){
        return attestationMapper.oneupdateattestation(attestation);
    }

    //更新
    public int updateattestation(Attestation attestation){
        return  attestationMapper.updateattestation(attestation);
    }

    //删除查询
    public List<Attestation> onedeleteattestation(Attestation attestation){
        return attestationMapper.onedeleteattestation(attestation);
    }

    //删除
    public int deleteattestation(Attestation attestation){
        return attestationMapper.deleteattestation(attestation);
    }

}
