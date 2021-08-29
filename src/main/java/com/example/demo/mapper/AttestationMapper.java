package com.example.demo.mapper;

import com.example.demo.entity.Attestation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttestationMapper {

    //查询父级
    List<Attestation> parentlist(Attestation attestation);
    //添加
    int insertattestation(Attestation attestation);
    //查询单条
    List<Attestation> oneattestation(Attestation attestation);
    //查询全部
    List<Attestation> allattestation(Attestation attestation);
    //更改菜单 鉴权
    int updateismenu(Attestation attestation);
    //更改排序
    int updatesort(Attestation attestation);
    //更新查询
    List<Attestation> oneupdateattestation(Attestation attestation);
    //更新
    int updateattestation(Attestation attestation);
    //删除查询
    List<Attestation> onedeleteattestation(Attestation attestation);
    //删除
    int deleteattestation(Attestation attestation);
}
