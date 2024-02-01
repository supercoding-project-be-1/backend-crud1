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
    @Column(name="id", unique = true)
    private Integer postId;

    @Column(name = "author", length =255, nullable = false)
    private String author;

    @Column(name = "title", length =255, nullable = false)
    private String title;
    @Column(name = "content", length =255, nullable = false)
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    //박슬기 추가. CommentEntity랑 연결하려고 해봤는데 오류나네요..ㅠㅠ..
//    @OneToMany(mappedBy = "memberEntity")
//    private List<CommentEntity> commentEntitiesList;

//    public void setPostBody(PostBody postBody) {
//        this.author = postBody.getAuthor();
//        this.title = postBody.getTitle();
//        this.content = postBody.getContent();
//    }
}
