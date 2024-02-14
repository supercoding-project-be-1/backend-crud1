package com.example.backeendproject1.repository.comment;

import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.member.MemberJpaRepository;
import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;
import org.hibernate.Hibernate;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
//@Builder
@Table(name="comments")
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    @Column(name = "content", length =255, nullable = false)
    private String content;
    @Column(name="author")
    private String author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true)
    private PostEntity postEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = true)
    private MemberEntity memberEntity;


    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;
//
//    public CommentEntity(Integer id, String content, String author, PostEntity postEntity, MemberEntity memberEntity) {
//        this.id = id;
//        this.content = content;
//        this.author = null;
//        this.postEntity = postEntity;
//        this.memberEntity = memberEntity;
////        this.createdAt = LocalDateTime.now();
//    }
//
//    public CommentEntity(Integer id, String content) {
//        this.id = id;
//        this.content = content;
//        this.author = null;
//        this.postEntity = null;
//        this.memberEntity = null;
//        this.createdAt = LocalDateTime.now();
//    }

//    public void setCommentBody(CommentBody commentBody) {
//        this.content = commentBody.getContent();
//        this.author =commentBody.getAuthor();
//        this.postEntity = 0;
//        this.memberEntity = 0;
//    }

//    public void setContent(String updatedContent) {
//    }

//    public CommentEntity(PostEntity postEntity, MemberEntity memberEntity) {
//        this.postEntity = postEntity,
//        this.memberEntity = memberEntity,
//        this.createdAt = LocalDateTime.now();
//    }
}
