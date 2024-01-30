package com.example.backeendproject1.repository.member;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.post.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@ToString
@Builder
@Entity
@Table(name="member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;

    @Column(name = "password", length =255, nullable = false)
    private String password;

    @Column(name = "nickname", length =255, nullable = false, unique = true)
    private String nickname;

    @Column(name = "authority", length =255)
    private String authority;

    @OneToMany(mappedBy = "memberEntity")
    private List<CommentEntity> commentEntitiesList;

    @OneToMany(mappedBy = "memberEntity")
    private List<PostEntity> postEntitiesList;

}



