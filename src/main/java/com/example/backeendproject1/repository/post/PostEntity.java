package com.example.backeendproject1.repository.post;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.web.dto.PostBody;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> comments; /*= new ArrayList<>();*/
}
