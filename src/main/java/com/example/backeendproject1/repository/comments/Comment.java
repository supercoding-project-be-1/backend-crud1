package com.example.backeendproject1.repository.comments;

import com.example.backeendproject1.repository.member.Member;
import com.example.backeendproject1.repository.posts.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="email")
    private Member member;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
