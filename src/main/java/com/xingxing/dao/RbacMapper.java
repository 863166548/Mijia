package com.xingxing.dao;

import com.xingxing.bean.dto.AccessDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RbacMapper {
    List<AccessDto> getUserAndAccess(Integer id);
}
