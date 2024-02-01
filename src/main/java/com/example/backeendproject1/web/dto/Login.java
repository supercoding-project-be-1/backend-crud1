package com.example.backeendproject1.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Login {
    @Schema(description = "email 이메일", example = "user@gmail.com.")
    private String email;
    @Schema(description = "password", example = "비밀번호")
    private String password;
}