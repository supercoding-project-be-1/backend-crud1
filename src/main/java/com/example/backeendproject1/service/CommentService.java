package com.example.backeendproject1.service;

import com.example.backeendproject1.repository.comments.Comment;
import com.example.backeendproject1.repository.posts.Post;
import com.example.backeendproject1.repository.posts.PostJpa;
import com.example.backeendproject1.web.dto.CommentsDto;
import com.example.backeendproject1.web.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostJpa postJpa;
    public ResponseDto findAllComment(Integer postId) {
            Optional<Post> postEntity = postJpa.findById(postId);
            List<Comment> comments= postEntity.get().getComments();

        List<CommentsDto> commentsDto = comments.stream().map((comment) ->
                    CommentsDto.builder()
                            .id(comment.getId())
                            .content(comment.getContent())
                            .author(comment.getMember().getEmail())
                            .postId(comment.getPost().getId())
                            .createdAt(comment.getCreatedAt())
                            .build()
        ).toList();

        return new ResponseDto(200, "댓글 조회 성공 ㅊㅊ", commentsDto);
    }
}
