package com.example.backeendproject1.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {
    private Integer code;
    private String message;
    private Object data;


    public ResponseDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDto(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
