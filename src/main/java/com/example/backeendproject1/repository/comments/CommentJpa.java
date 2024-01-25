package com.example.backeendproject1.repository.comments;

import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpa extends JpaRepository<Comments, Integer> {
}
