package com.example.backeendproject1.web.controller;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.post.PostJpaRepository;
import com.example.backeendproject1.service.comment.CommentService;
import com.example.backeendproject1.service.mapper.CommentMapper;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;


    @Operation(summary = "새로운 댓글 등록")
    @PostMapping("/{postId}/comments")
    public String addCommentToPost(@PathVariable String postId, @RequestBody CommentBody commentBody) {
        commentService.addCommentToPost(postId, commentBody);
      System.out.println("commentBody >> "+commentBody);
        return null;
     //   return "댓글이 성공적으로 작성되었습니다.";
    }

//    @Operation(summary = "새로운 댓글 등록")
//    @PostMapping("/{postId}/comments")
//    public String registerComment(@PathVariable String postId, @RequestBody CommentBody commentBody) {
//        System.out.println("commentBody >> "+commentBody);
//        return null;}

    @Operation(summary = "포스트Id에 등록된 모든 댓글 조회")
    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsForPost(@PathVariable("postId") String postId) {
        return commentService.getCommentsForPost(postId);
    }

    @Operation(summary = "기존 댓글의 글을 수정") //요청문에 post를 넣어야하나?
    @PutMapping("/comments/{commentId}")
    public void updateComment(@PathVariable("commentId") String commentId, @RequestBody String newContent) {
        commentService.updateComment(commentId, newContent);
    }

    @Operation(summary = "단일 댓글 id로 삭제")
    @DeleteMapping("/comments/{id}")
    public String deleteCommentByPathId
            (@Parameter(name = "id", description = "comment ID", example = "1")
             @PathVariable String id) {
        commentService.deleteComment(id);
        String responseMessage = "댓글 id: " + id + " 성공적으로 삭제되었습니다.";
        return responseMessage;
    }
}



//    추가로 작성할 수 있는 코드들
//comment id로 찾기, 닉네임으로 찾기
    //comment 대댓글달기
    //좋아요 하기

//주석처리한 코드



//    @Operation(summary = "모든 댓글 조회")
//    @GetMapping("/comments")
//    public List<Comment> findAllComments(){
//        List<Comment> comments = commentService.findAllComments();
//        return comments;
//    }
//    @Operation(summary = "새로운 댓글 등록")
//    @PostMapping("/comments")
//    public String registerComment(@RequestBody CommentBody commentBody) {
//        Integer commentId = commentService.saveComment(commentBody);
//        return "댓글 ID: " + commentId + "이 성공적으로 작성되었습니다.";
//    }
//
//    @Operation(summary = "기존 댓글의 글을 수정")
//    @PutMapping("/comments/{id}")
//    public Comment updateComment(@PathVariable("id") String id, @RequestBody CommentBody commentBody){
//        return commentService.updateComment(id, commentBody);
//    }
//
//    @Operation(summary = "작성자(author:닉네임)로 댓글 조회")
//    @GetMapping("/comments-author")
//    public List<Comment> findCommentsByAuthor(
//            @RequestParam("author") String authorNickName){
//        List<Comment> comments = commentService.findCommentsByAuthor(authorNickName);
//        return comments;
//    }
//}