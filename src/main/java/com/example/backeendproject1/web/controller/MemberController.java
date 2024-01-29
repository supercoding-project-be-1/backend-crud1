package com.example.backeendproject1.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.backeendproject1.service.MemberService;
import com.example.backeendproject1.web.vo.MemberListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@ResponseBody
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberListVo>> selectMember(@RequestParam(value = "email") String email) {
        try {
            List<MemberListVo> response = memberService.selectMemberList(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/checkemail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        try {
            boolean isDuplicate = memberService.checkEmail(email);
            return ResponseEntity.ok(isDuplicate);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAuthority/{email}")
    public ResponseEntity<MemberListVo> getAuthority(@PathVariable("email") String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberListVo getAuthority = memberService.getAuthority(email);
            return ResponseEntity.ok(getAuthority);
        } catch (Exception e) {
            // 예외 발생 시 로그에 기록
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addMember")
    public ResponseEntity<Map<String, Object>> addMember(@RequestBody MemberListVo memberListVo) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> insertMember = memberService.insertMember(memberListVo);
            if ((boolean) insertMember.get("success")) {
                response.put("success", true);
                response.put("message", "사용자 추가 성공");
            } else {
                response.put("success", false);
                response.put("message", "사용자 추가 실패");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "사용자 추가 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}