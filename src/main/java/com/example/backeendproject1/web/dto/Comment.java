package com.example.backeendproject1.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Comment {
    @Schema(description = "ID", example = "1")
    private Integer id;
    @Schema(description = "Comment 내용", example = "감사합니다.")
    private String content;
    @Schema(description = "author", example = "닉네임")
    private String author;
    @Schema(description = "Post Id", example = "댓글이 달린 포스팅 글Id")
    private Integer postId;
    @Schema(description = "Meber Id", example = "멤버아이디")
    private Integer memberId;
    @Schema(description = "Time", example = "댓글 작성 시간")
    private LocalDateTime createdAt;

}


