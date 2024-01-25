package com.example.backeendproject1.web.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDto {
    private Integer id;
    private String content;
    private String author;
    private Integer postId;
    private LocalDateTime createdAt;

}
