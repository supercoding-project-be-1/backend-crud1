package com.example.backeendproject1.service.member;

import com.example.backeendproject1.web.vo.MemberListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberListMapper memberListMapper;

    public List<MemberListVo>selectMemberList(String email) {
        List<MemberListVo> memberList = memberListMapper.selectMemberList(email);
        return memberList;
    }
    public boolean checkEmail(String email) {
        int count = memberListMapper.checkEmail(email);
        return count > 0; // count가 1 이상이면 중복, 0이면 중복되지 않음
    }
    public MemberListVo getAuthority(String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberListVo authority = memberListMapper.getAuthority(email);
            response.put("authority", authority);
            return authority;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "권한 정보 가져오기 실패");
        }
        return null;
    }

    public Map<String, Object> insertMember(MemberListVo memberListVo) {
        Map<String, Object> response = new HashMap<>();
        try {
            int rowsAffected = memberListMapper.insertMember(memberListVo);

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
