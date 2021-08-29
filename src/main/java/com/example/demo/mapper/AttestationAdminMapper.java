package com.example.demo.mapper;

import com.example.demo.entity.AttestationAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttestationAdminMapper {

    //单个查询
    List<AttestationAdmin> oneattestationadmin(AttestationAdmin attestationAdmin);

    //添加
    int insertattestationadmin(AttestationAdmin attestationAdmin);

    //查询全部
    List<AttestationAdmin> allattestationadmin(AttestationAdmin attestationAdmin);

    //更新查询
    List<AttestationAdmin> oneupdateattestationadmin(AttestationAdmin attestationAdmin);

    //更新
    int updateattestationadmin(AttestationAdmin attestationAdmin);

    //删除
    int deleteattestationadmin(AttestationAdmin attestationAdmin);

}
