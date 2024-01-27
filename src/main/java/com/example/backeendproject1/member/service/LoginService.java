package com.example.backeendproject1.member.service;


import com.example.backeendproject1.member.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public ResponseEntity<Map<String, Object>> selectLoginCheck (String email, String password) {
        try {
            Map<String, Object> member = loginMapper.selectMemberInfo(email);

            if(member != null) {
                String storedPassword = (String) member.get("password");

                if(!password.equals(storedPassword)) {
                    Map<String, Object> response = new HashMap<>();
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                }else {
                    Map<String, Object> response = new HashMap<>();
                    return ResponseEntity.ok(response);
                }
            }else {
                Map<String, Object> response = new HashMap<>();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
