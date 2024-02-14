package com.example.backeendproject1.service.mapper;

import com.example.backeendproject1.repository.member.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MemberListMapper {

    List<MemberEntity> selectMemberList(@RequestParam("email") String email);
    int checkEmail (String email);
    int insertMember (MemberEntity memberEntity);
    MemberEntity getAuthority(@Param("email") String email);
}
