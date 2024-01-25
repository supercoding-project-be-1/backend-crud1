package com.example.backeendproject1.service;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    //싱글톤
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    //메소드
    @Mapping(target="content", source = "content")
    @Mapping(target="author", source = "author")
    @Mapping(target="postId", source = "postId")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target="content", source = "commentBody.content")
    @Mapping(target="author", source = "commentBody.author")
    @Mapping(target="postId", source = "commentBody.postId")
    CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody);

}
