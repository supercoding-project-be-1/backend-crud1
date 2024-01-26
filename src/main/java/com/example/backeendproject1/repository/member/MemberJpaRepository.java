package com.example.backeendproject1.repository.member;

import com.example.backeendproject1.repository.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<CommentEntity, Integer> {
}
