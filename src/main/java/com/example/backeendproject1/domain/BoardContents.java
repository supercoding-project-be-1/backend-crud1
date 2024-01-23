package com.example.backeendproject1.domain;

import java.time.LocalDateTime;

public class BoardContents {
    private Long id;
    private Board board;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
