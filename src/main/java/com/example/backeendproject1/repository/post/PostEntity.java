package com.example.backeendproject1.repository.post;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.web.dto.PostBody;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
//    @OneToMany
//    @JoinColumn(name="author", referencedColumnName = "nickname", nullable = false)
    private Integer id;

    //author
//    @ManyToOne
//    @JoinColumn(name="email")
//    private Member member;
//    //mappedby해주기

    @Column(name = "author")
    private String author;

    //nickname
    //nickname은 member, comment 랑도 연결해야 하는데..
//    @ManyToOne
//    @JoinColumn(name="nickname")
//    private Member member;
    //    //mappedby해주기
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //박슬기 추가. CommentEntity랑 연결하려고 해봤는데 오류나네요..ㅠㅠ..
//    @OneToMany(mappedBy = "memberEntity")
//    private List<CommentEntity> commentEntitiesList;

    public void setPostBody(PostBody postBody) {
        this.author = postBody.getAuthor();
        this.title = postBody.getTitle();
        this.content = postBody.getContent();
    }
}
