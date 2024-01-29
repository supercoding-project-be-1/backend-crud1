package com.example.backeendproject1.web.vo;


import lombok.Data;

@Data
public class LoginVo {
    private String email;
    private String password;
    private String nickname;
    private String Authority;
}
