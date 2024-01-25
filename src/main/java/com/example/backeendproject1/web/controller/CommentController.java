package com.example.backeendproject1.web.controller;

import com.example.backeendproject1.service.CommentService;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;


    @Operation(summary = "모든 댓글 조회")
    @GetMapping("/comments")
    public List<Comment> findAllComments(){
        List<Comment> comments = commentService.findAllComments();
        return comments;
    }
    @Operation(summary = "새로운 댓글 등록")
    @PostMapping("/comments")
    public String registerComment(@RequestBody CommentBody commentBody) {
        Integer commentId = commentService.saveComment(commentBody);
        return "댓글 ID: " + commentId + "이 성공적으로 작성되었습니다.";
    }
    @Operation(summary = "기존 댓글의 글을 수정")
    @PutMapping("/comments/:comment_id")
    public Comment updateComment(@PathVariable Integer id, @RequestBody CommentBody commentBody){
        return commentService.updateComment(id, commentBody);
    }
    @Operation(summary = "단일 댓글 id로 삭제")
    @DeleteMapping("/comments/:comments_id")
    public String deleteCommentByPathId(@Parameter(name = "id", description = "comment ID", example = "1") @PathVariable String id){
        commentService.deleteComment(id);
        String responseMessage = "Object with id = " + id + " has been deleted";
        return responseMessage;
    }

}
