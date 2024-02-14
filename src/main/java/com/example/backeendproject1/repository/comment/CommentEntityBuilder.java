//package com.example.backeendproject1.repository.comment;
//
//import com.example.backeendproject1.repository.member.MemberEntity;
//import com.example.backeendproject1.repository.post.PostEntity;
//
//import java.time.LocalDateTime;
//
//public class CommentEntityBuilder {
//    private Integer id;
//    private String content;
//    private String author;
//    private PostEntity postEntity;
//    private MemberEntity memberEntity;
//    private LocalDateTime createdAt;
//
//    public CommentEntityBuilder id(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public CommentEntityBuilder content(String content) {
//        this.content = content;
//        return this;
//    }
//
//    public CommentEntityBuilder author(String author) {
//        this.author = author;
//        return this;
//    }
//
//    public CommentEntityBuilder postEntity(PostEntity postEntity) {
//        this.postEntity = postEntity;
//        return this;
//    }
//
//    public CommentEntityBuilder memberEntity(MemberEntity memberEntity) {
//        this.memberEntity = memberEntity;
//        return this;
//    }
//
//    public CommentEntityBuilder createdAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//        return this;
//    }
//
//    public CommentEntity build() {
//        return new CommentEntity(id, content, author, postEntity, memberEntity, createdAt);
//    }
//}