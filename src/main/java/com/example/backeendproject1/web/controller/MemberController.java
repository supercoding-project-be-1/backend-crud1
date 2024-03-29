package com.example.backeendproject1.web.controller;

import com.example.backeendproject1.repository.member.MemberEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.backeendproject1.service.member.MemberService;
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
    public ResponseEntity<List<MemberEntity>> selectMember(@RequestParam(value = "email") String email) {
        try {
            List<MemberEntity> response = memberService.selectMemberList(email);
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
    public ResponseEntity<MemberEntity> getAuthority(@PathVariable("email") String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberEntity getAuthority = memberService.getAuthority(email);
            return ResponseEntity.ok(getAuthority);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addMember")
    public ResponseEntity<Map<String, Object>> addMember(@RequestBody MemberEntity memberEntity) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> insertMember = memberService.insertMember(memberEntity);
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