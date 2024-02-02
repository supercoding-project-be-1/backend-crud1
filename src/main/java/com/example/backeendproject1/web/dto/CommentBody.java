package com.example.backeendproject1.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentBody {
    @Schema(description = "Comment 내용", example = "감사합니다.")
    private String content;
    @Schema(description = "author", example = "닉네임")
    private String author;
    @Schema(description = "Post Id", example = "댓글이 달린 포스팅 글 Id")
    private Integer postId;
    @Schema(description = "member ID", example = "멤버 ID")
    private Integer memberId;

}
