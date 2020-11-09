package com.xingxing.dao;

import com.xingxing.bean.SysUserAccess;
import com.xingxing.bean.SysUserAccessExample;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysUserAccessMapper {
    long countByExample(SysUserAccessExample example);

    int deleteByExample(SysUserAccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserAccess record);

    int insertSelective(SysUserAccess record);

    List<SysUserAccess> selectByExample(SysUserAccessExample example);

    SysUserAccess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserAccess record, @Param("example") SysUserAccessExample example);

    int updateByExample(@Param("record") SysUserAccess record, @Param("example") SysUserAccessExample example);

    int updateByPrimaryKeySelective(SysUserAccess record);

    int updateByPrimaryKey(SysUserAccess record);

    int deleteByUserId(int id);

    int insertForeach(ArrayList<SysUserAccess> list);

    int selectUserAccess(Integer userId);

    int selectuseraccess(Integer userId);

}