package com.example.backeendproject1.service;

import com.example.backeendproject1.web.dto.CommentBody;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.webjars.NotFoundException;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

public class CommentService {
    public List<Comment> findAllComments() {
        List<CommentEntity> commentEntities = commentJpaRepository.findAll();
        if(commentEntities.isEmpty()) throw new NotFoundException("아직 댓글이 없습니다.");

        return commentEntities.stream().map(CommentMapper.INSTANCE:::commentEntitiesToComment).collect(Collectors.toList());
    }

    public Integer createComment(CommentBody commentBody) {
    }

    public com.example.backeendproject1.web.dto.Comment updateComment(Integer id, CommentBody commentBody) {
    }
}
