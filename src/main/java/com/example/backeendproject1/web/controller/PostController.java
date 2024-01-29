package com.example.backeendproject1.web.controller;


import com.example.backeendproject1.service.post.PostService;
import com.example.backeendproject1.web.dto.Post;
import com.example.backeendproject1.web.dto.PostBody;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "모든 게시글 조회")
    @GetMapping("/posts/find")
    public List<Post> findAllPosts(){
        List<Post> posts= postService.findAllPosts();
        return posts;
    }

    @Operation(summary = "새로운 게시글 작성")
    @PostMapping("/posts/register")
    public String registerPost(@RequestBody PostBody postBody){
        Integer postId= postService.savePost(postBody);
        return "PostID: "+ postId + "번 게시물 성공적으로 작성되었습니다";
    }

    @Operation(summary = "기존 게시글 수정")
    @PutMapping("/posts/update/{postId}") //❓왜 post를 반환해?
    public Post updatePost(@PathVariable("id") Integer id //여기는 id String인데 왜 서비스는 interger?
            , @RequestBody PostBody postBody){
        return postService.updatePost(id, postBody);
    }

    @Operation(summary = "기존 게시글 삭제")
    @DeleteMapping("/posts/delete/{postId}")
    public String deletePostByPathId(@PathVariable String postId){
        postService.deletePost(Integer.valueOf(postId));
        return "아이디 "+ postId + "번 게시물이 삭제되었습니다.";
    }
}
