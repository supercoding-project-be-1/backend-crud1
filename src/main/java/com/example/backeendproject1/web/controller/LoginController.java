package com.example.backeendproject1.web.controller;

import com.example.backeendproject1.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;


    @PostMapping("/login" )
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) throws Exception{
        try {
            String email = (String) requestBody.get("email");
            String password = (String) requestBody.get("password");

            ResponseEntity<Map<String, Object>> responseEntity = loginService.selectLoginCheck(email, password);

            HttpSession httpSession = request.getSession(false);
            if (httpSession != null) {
                httpSession.invalidate();
            }

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                int sessionExpirationTime = 3600;
                HttpSession session = request.getSession(true);
                session.setAttribute("loginSession", email);

                Map<String, Object> response = new HashMap<>();
                response.put("sessionId", session.getId());
                response.put("sessionExpiredTime", sessionExpirationTime);
                response.put("message", "success");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "fail");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "signup not implemented");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
    }

    @GetMapping("/checkSession")
    public boolean checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("email") != null;
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/logout")
//    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
//        Collections Collections = null;
//        if (session != null && session.getAttribute("loginSession") != null) {
//            session.invalidate();
//            return ResponseEntity.ok(java.util.Collections.singletonMap("message", "success"));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(java.util.Collections.singletonMap("message", "No active session"));
//        }
//    }

}