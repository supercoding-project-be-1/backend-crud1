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
 //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //메소드 //@필드네임이 같아서 @Mapping생략가능?

    @Mapping(target="content", source = "content")
    @Mapping(target="author", source = "memberEntity.nickname")
    @Mapping(target = "postId",source = "postEntity.postId")
    @Mapping(target = "memberId",source = "memberEntity.id")
    Comment commentEntityToComment(CommentEntity commentEntity);

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ에러해결중ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
@Mapping(target = "createdAt", ignore=true)
    @Mapping(target="id", source="id")
    @Mapping(target="content", source = "commentBody.content")
    @Mapping(target = "memberEntity.id",source = "commentBody.memberId")
    @Mapping(target = "postEntity.postId",source = "commentBody.postId")
    @Mapping(target="memberEntity.nickname", source = "commentBody.author")
    // @Mapping(target="id", ignore=true)
  @Mapping(target = "createdAt", source="createdAt", qualifiedByName = "convert")
////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//   CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody);
////
//    default CommentEntity map(Integer id, CommentBody commentBody) {
//        if (id == null && commentBody == null) {
//            return null;
//        }
//
//        CommentEntity.CommentEntityBuilder commentEntity = CommentEntity.builder();
//
//        if (commentBody != null) {
//            commentEntity.memberEntity(commentBodyToMemberEntity(commentBody));
//            commentEntity.postEntity(commentBodyToPostEntity(commentBody));
//            commentEntity.content(commentBody.getContent());
//            commentEntity.author(commentBody.getAuthor());
//        }
//
//        commentEntity.id(id);
//        return commentEntity.build();
//
//        }
//
//    PostEntity commentBodyToPostEntity(CommentBody commentBody);
//
//    MemberEntity commentBodyToMemberEntity(CommentBody commentBody);
//
//
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    default CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody){
        CommentEntity entity= new CommentEntity();
        MemberEntity memberEntity = new MemberEntity();
         if (memberEntity != null) {
          entity.setMemberEntity(memberEntity);
          entity.setAuthor(memberEntity.getNickname());
      }
       entity.getPostEntity();
        entity.setContent(commentBody.getContent());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

//    @Mapping(target = "createdAt", ignore = true)
////    @Mapping(target = "commentBody", source = "")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "postEntity", source = "commentBody.postId", qualifiedByName = "toPostEntity")
//    @Mapping(target = "memberEntity", source = "commentBody.memberId", qualifiedByName = "toMemberEntity")
//    CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody);
//
//    @Mapping(target = "postId", source = "postEntity.postId")
//    @Mapping(target = "memberId", source = "memberEntity.id")
//    Comment commentEntityToComment(CommentEntity commentEntity);
//
//    @Named("toPostEntity")
//    default PostEntity toPostEntity(Integer postId) {
//        PostEntity postEntity = new PostEntity();
//        postEntity.setPostId(postId);
//        return postEntity;
//    }
//
//    @Named("toMemberEntity")
//    default MemberEntity toMemberEntity(Integer memberId) {
//        MemberEntity memberEntity = new MemberEntity();
//        memberEntity.setId(memberId);
//        return memberEntity;
//    }
/////////////////////
//    @Named("convert")
//    static String localDateTimeToString(LocalDateTime localDateTime) {
//        return localDateTime != null
//                ? localDateTime.format(formatter)
//                : ""; // Return an empty string or any default value you prefer for null LocalDateTime
//    }

}