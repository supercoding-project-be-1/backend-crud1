package com.example.backeendproject1.repository.posts;

import com.example.backeendproject1.repository.comments.Comment;
import com.example.backeendproject1.repository.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "posts")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="email")
    private Member member;

    @Column(name= "title")
    private String title;

    @Column(name= "content")
    private String content ;

    @Column(name= "created_at")
    private LocalDateTime createdAt;

    //역방향
    //mappedBy= 정방향 column 명
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;




}
