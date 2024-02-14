package com.example.backeendproject1.service.mapper;


import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.web.dto.Post;
import com.example.backeendproject1.web.dto.PostBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Mapper
public interface PostMapper {
    //singleTon
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    //method
    //Entity => DTO

    @Mapping(target="id", source = "postId")
    @Mapping(target="memberId", source = "member.id")
//    @Mapping(target="author", source = "author")
    @Mapping(target="author", source = "member.nickname")
    @Mapping(target="title", source = "title")
    @Mapping(target="content", source = "content")
    Post postEntityToPostDto(PostEntity postEntity); //그럼 이 post는 DTO지?

//    이거랑 같은 의미?
//    public static Post postEntityToPostDto(PostEntity postEntity){
//        Post post= new Post();
//        post.setAuthor(postEntity.getMember().getNickname());
//        post.setTitle(postEntity.getTitle());
//        post.setContent(postEntity.getContent());
//        return post;
//    }

    //PostBody
    //DTO => Entity
//    @Mapping(target="author", source = "postBody.author")
//    @Mapping(target="title", source = "postBody.title")
//    @Mapping(target="content", source = "postBody.content")
    default PostEntity idAndPostBodyToPostEntity(MemberEntity member, PostBody postBody){
        PostEntity entity= new PostEntity();
        entity.setMember(member);
        entity.setAuthor(member.getNickname());
        entity.setTitle(postBody.getTitle());
        entity.setContent(postBody.getContent());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

//    Post postEntityToPostDto(PostEntity postEntity);
}
