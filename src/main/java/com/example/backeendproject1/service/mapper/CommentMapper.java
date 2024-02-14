package com.example.backeendproject1.service.mapper;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import com.example.backeendproject1.web.dto.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface CommentMapper {

    //싱글톤
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    @Mapping(target="content", source = "content")
    @Mapping(target="author", source = "memberEntity.nickname")
    @Mapping(target = "postId",source = "postEntity.postId")
    @Mapping(target = "memberId",source = "memberEntity.id")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target="createdAt", ignore = true)
    @Mapping(target = "content", source = "commentBody.content")
    @Mapping(target = "memberEntity.id", source = "commentBody.memberId")
   @Mapping(target = "postEntity.postId", source = "commentBody.postId")
    @Mapping(target = "memberEntity.nickname", source = "commentBody.author")
    CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody);}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//메소드 //@필드네임이 같아서 @Mapping생략가능?
//@Mapping(target = "memberId", source = "memberEntity.id")
//@Mapping(target = "postId", source = "postEntity.postId")
//Comment commentEntityToComment(CommentEntity commentEntity);
//
//    default CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody) {
//        CommentEntity entity = new CommentEntity();
//        entity.setId(id);
//        // PostEntity postId = new PostEntity();
//        MemberEntity memberEntity = new MemberEntity();
//        entity.setMemberEntity(memberEntity);
//        entity.setAuthor(memberEntity.getNickname());
//        entity.getPostEntity();
//        entity.setContent(commentBody.getContent());
//        return entity;
//    }
//}

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm


//    @Mapping(target = "memberEntity.id",source = "commentBody.memberId")
//    @Mapping(target = "postEntity.postId",source = "commentBody.postId")
//    @Mapping(target = "memberEntity.nickname",source = "commentBody.author")
//    CommentEntity idAndCommentToCommentEntity(Integer id, CommentBody commentBody);