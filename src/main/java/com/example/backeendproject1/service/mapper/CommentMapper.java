package com.example.backeendproject1.service.mapper;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
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
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "convert")


    //메소드 //@필드네임이 같아서 @Mapping생략가능?
    @Mapping(target="content", source = "content")
    @Mapping(target="author", source = "author")
    @Mapping(target="postId", source = "postId")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target="content", source = "commentBody.content")
    @Mapping(target="author", source = "commentBody.author")
    @Mapping(target="postId", source = "commentBody.postId")
    CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody);


//    @Named("convert")
//    static String localDateTimeToString(LocalDateTime localDateTime) {
//        return localDateTime != null
//                ? localDateTime.format(formatter)
//                : ""; // Return an empty string or any default value you prefer for null LocalDateTime
//    }

}
