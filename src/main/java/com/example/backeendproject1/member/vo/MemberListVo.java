package com.example.backeendproject1.member.vo;

import lombok.Data;

@Data
public class MemberListVo {

    public MemberListVo() {

    }

    public MemberListVo(String email) {
        this.email = email;
    }

    private String nickname;
    private String email;
    private String password;
    private String authority;
}
