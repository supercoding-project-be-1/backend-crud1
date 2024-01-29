package com.example.backeendproject1.service.mapper;


import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.web.dto.Post;
import com.example.backeendproject1.web.dto.PostBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    //singleTon
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    //method
    //Entity => DTO
    @Mapping(target="author", source = "author")
    @Mapping(target="title", source = "title")
    @Mapping(target="content", source = "content")
    Post postEntityToPostDto(PostEntity postEntity); //그럼 이 post는 DTO지?

    //이거랑 같은 의미?
//    public static Post postEntityToPostDto(PostEntity postEntity){
//        Post post= new Post();
//        post.setAuthor(postEntity.getAuthor());
//        post.setNickname(postEntity.getNickname());
//        post.setTitle(postEntity.getTitle());
//        post.setContent(postEntity.getContent());
//        return post;
//    }

    //PostBody
    //DTO => Entity
//    @Mapping(target="author", source = "postBody.author")
//    @Mapping(target="title", source = "postBody.title")
//    @Mapping(target="content", source = "postBody.content")
    default PostEntity idAndPostBodyToPostEntity(Integer id, PostBody postBody){
        PostEntity entity= new PostEntity();
        entity.setId(id);
        entity.setAuthor(postBody.getAuthor());
        entity.setTitle(postBody.getTitle());
        entity.setContent(postBody.getContent());
        return entity;
    }
}
