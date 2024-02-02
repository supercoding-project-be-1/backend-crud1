package com.example.backeendproject1.service.mapper;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import com.example.backeendproject1.web.dto.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper

public interface CommentMapper {

    //싱글톤
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "convert")


    //메소드 //@필드네임이 같아서 @Mapping생략가능?
    @Mapping(target = "memberId",source = "memberEntity.id")
    @Mapping(target = "postId",source = "postEntity.postId")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target = "memberEntity.id",source = "commentBody.memberId")
    @Mapping(target = "postEntity.postId",source = "commentBody.postId")
    CommentEntity idAndCommentBodyToCommentEntity(Object o, CommentBody commentBody);

//   CommentEntity idAndCommentToCommentEntity(Integer id, CommentBody commentBody);



//    @Named("convert")
//    static String localDateTimeToString(LocalDateTime localDateTime) {
//        return localDateTime != null
//                ? localDateTime.format(formatter)
//                : ""; // Return an empty string or any default value you prefer for null LocalDateTime
//    }

}
