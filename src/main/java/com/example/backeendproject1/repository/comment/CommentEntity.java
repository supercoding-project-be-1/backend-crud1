package com.example.backeendproject1.repository.comment;

import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.web.dto.CommentBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.hibernate.Hibernate;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@Entity
@Table(name="comments")

public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", length =255, nullable = false)
    private String content;
    @Column(name="author", nullable = false)
    private String author;
    @Column(name = "post_id", unique = true, nullable = false)
    private Integer postId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="author", referencedColumnName = "nickname", nullable = false)
//    private MemberEntity memberEntity;
//    //mappedby해주기
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id", nullable = false)
//    private PostEntity postEntity;
//    //mappedby해주기

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    public void setCommentBody(CommentBody commentBody) {
        this.content = commentBody.getContent();
        this.author = commentBody.getAuthor();
        this.postId = commentBody.getPostId();
    }
//    public void setCommentBody(CommentBody commentBody) {
//        this.content = commentBody.getContent();
//        this.memberEntity = commentBody.getAuthor();
//        this.postEntity = commentBody.getPostId();
//    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
//            return false;
//        }
//        CommentEntity commentEntity = (CommentEntity) o;
//        return id != null && Objects.equals(id, commentEntity.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }

}
