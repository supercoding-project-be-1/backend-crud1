package com.example.backeendproject1.member.mapper;

import com.example.backeendproject1.member.vo.MemberListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MemberListMapper {

    List<MemberListVo> selectMemberList(@RequestParam("email") String email);
    int checkEmail (String email);
    int insertMember (MemberListVo memberListVo);
    MemberListVo getAuthority(@Param("email") String email);
}
