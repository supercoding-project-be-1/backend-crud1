package com.example.backeendproject1.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class CommentsDto {
    private Integer id;
    private String content;
    private String author;
    private Integer postId;
    private LocalDateTime createdAt;

}

