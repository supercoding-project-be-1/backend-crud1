package com.example.backeendproject1.service;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.comment.CommentJpaRepository;
import com.example.backeendproject1.service.exceptions.NotAcceptException;
import com.example.backeendproject1.service.exceptions.NotFoundException;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;


    public List<Comment> findAllComments() {
        List<CommentEntity> commentEntities = commentJpaRepository.findAll();
        if(commentEntities.isEmpty()) throw new NotFoundException("아직 댓글이 없습니다.");

        return commentEntities.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
    }


    public Integer saveComment(CommentBody commentBody) {
        CommentEntity commentEntity = CommentMapper.INSTANCE.idAndCommentBodyToCommentEntity(null,commentBody );
        CommentEntity commentEntityCreated;

        try{
            commentEntityCreated = commentJpaRepository.save(commentEntity);
        }catch(RuntimeException exception){
            throw new NotAcceptException("댓글을 업로드하는 도중에 Error가 발생했습니다.");
        }
        return commentEntityCreated.getId();
    }


    @Transactional
    public Comment updateComment(Integer commentId, CommentBody commentBody) {
    //    Integer idInt = Integer.valueOf(commentId);
        CommentEntity commentEntityUpdated = commentJpaRepository.findById(commentId)
                               .orElseThrow(() ->new NotFoundException("해당 Id: " + commentId + "의 Comment를 찾을 수 없습니다."));
        commentEntityUpdated.setCommentBody(commentBody);
        return CommentMapper.INSTANCE.commentEntityToComment(commentEntityUpdated);
    }

    public void deleteComment(String id) {
    }
}
