package com.example.backeendproject1.repository.member;

import com.example.backeendproject1.repository.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Integer> {
    static Optional<MemberEntity> findByNickname(String nickname) {
        return null;
    }

}
