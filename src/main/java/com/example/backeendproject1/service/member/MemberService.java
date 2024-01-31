package com.example.backeendproject1.service.member;

import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.member.MemberJpaRepository;
import com.example.backeendproject1.service.mapper.MemberListMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberListMapper memberListMapper;

    public List<MemberEntity>selectMemberList(String email) {
        List<MemberEntity> memberList = memberListMapper.selectMemberList(email);
        return memberList;
    }
    public boolean checkEmail(String email) {
        int count = memberListMapper.checkEmail(email);
        return count > 0; // count가 1 이상이면 중복, 0이면 중복되지 않음
    }
    public MemberEntity getAuthority(String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberEntity authority = memberListMapper.getAuthority(email);
            response.put("authority", authority);
            return authority;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "권한 정보 가져오기 실패");
        }
        return null;
    }

    public Map<String, Object> insertMember(MemberEntity memberEntity) {
        Map<String, Object> response = new HashMap<>();
        try {
            int rowsAffected = memberListMapper.insertMember(memberEntity);

            if (rowsAffected > 0) {
                response.put("success", true);
            } else {
                response.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        }
        return response;
    }
}
