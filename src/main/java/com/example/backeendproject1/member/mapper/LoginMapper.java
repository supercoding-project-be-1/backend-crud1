package com.example.backeendproject1.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

@Mapper
public interface LoginMapper {
    Map<String, Object> selectMemberInfo(@Param("email")String email);
}