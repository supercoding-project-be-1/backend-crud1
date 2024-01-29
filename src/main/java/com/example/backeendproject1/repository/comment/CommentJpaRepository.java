package com.example.backeendproject1.repository.comment;

import com.example.backeendproject1.web.dto.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findCommentEntitiesByAuthor(String author);

    List<CommentEntity> findByParentId(Integer parentId);
}
