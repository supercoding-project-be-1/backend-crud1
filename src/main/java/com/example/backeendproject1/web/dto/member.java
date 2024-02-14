package com.example.backeendproject1.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@ToString

public class member {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private String authority;
}
