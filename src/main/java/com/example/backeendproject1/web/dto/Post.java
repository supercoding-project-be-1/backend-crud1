package com.example.backeendproject1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Integer memberId;

}
