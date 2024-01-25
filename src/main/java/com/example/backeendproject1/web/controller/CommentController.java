package com.example.backeendproject1.web.controller;

import com.example.backeendproject1.service.CommentService;
import com.example.backeendproject1.web.dto.LoginReqDto;
import com.example.backeendproject1.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments/{postId}")
    public ResponseDto findAllComment(
            @PathVariable("postId") Integer postId
            ){

        return commentService.findAllComment(postId);
    }

}
