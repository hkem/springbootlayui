package com.example.demo.mapper;

import com.example.demo.entity.AttestationGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttestationGroupMapper {

    //单个查询
    List<AttestationGroup> oneattestationgroup(AttestationGroup attestationGroup);

    //添加
    int insertattestationgroup(AttestationGroup attestationGroup);

    //查询全部
    List<AttestationGroup> allattestationgroup(AttestationGroup attestationGroup);

    //更新查询
    List<AttestationGroup> oneupdateattestationgroup(AttestationGroup attestationGroup);

    //更新
    int updateattestationgroup(AttestationGroup attestationGroup);

    //删除
    int deleteattestationgroup(AttestationGroup attestationGroup);

}
