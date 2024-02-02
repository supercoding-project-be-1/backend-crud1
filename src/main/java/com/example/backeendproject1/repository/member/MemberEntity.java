package com.example.backeendproject1.repository.member;

import com.example.backeendproject1.repository.comment.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id /*@Column(name = "id")*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "authority", nullable = false)
    private String authority = "DEFAULT_ROLE";

//    @Column(nullable = false)
//    private String authority;
//    @Column(name = "created_at", nullable = true)
//    private LocalDateTime createdAt;

}

