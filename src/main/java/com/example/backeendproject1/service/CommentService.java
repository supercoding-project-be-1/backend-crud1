package com.example.backeendproject1.service;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.comment.CommentJpaRepository;
import com.example.backeendproject1.service.exceptions.NotAcceptException;
import com.example.backeendproject1.service.exceptions.NotFoundException;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import jakarta.persistence.NoResultException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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
//포스트가 삭제됐을 때, 에러메세지 뜨는 예외 잡기..?!
        try{
            commentEntityCreated = commentJpaRepository.save(commentEntity);
        }catch (DataIntegrityViolationException ex) {
            throw new NotAcceptException("관련된 게시글을 찾을 수 없습니다 : 데이터 무결성 제약조건 위반");
        }catch(RuntimeException exception){
            throw new NotAcceptException("댓글을 업로드하는 도중에 알 수 없는 Error가 발생했습니다. ");
        }
        return commentEntityCreated.getId();
    }


    @Transactional
    public Comment updateComment(String id, CommentBody commentBody) {
        Integer idInt = Integer.valueOf(id);
        CommentEntity commentEntityUpdated = commentJpaRepository.findById(idInt)
                               .orElseThrow(() ->new NotFoundException("해당 Id: " + idInt + "의 Comment를 찾을 수 없습니다."));
        commentEntityUpdated.setCommentBody(commentBody);
        return CommentMapper.INSTANCE.commentEntityToComment(commentEntityUpdated);
    }

    public void deleteComment(String id) {
        try {
            Integer idInt = Integer.valueOf(id);
            commentJpaRepository.deleteById(idInt);
        } catch (NumberFormatException e) {
            throw new NotAcceptException("comment id 형식이 올바르지 않습니다.");
        }
    }


    public List<Comment> findCommentsByAuthor(String authorNickName) {
       try {
           List<CommentEntity> commentEntities = commentJpaRepository.findCommentEntitiesByAuthor(authorNickName);

       if(commentEntities.isEmpty()){
           throw new NotFoundException("아직 댓글이 없습니다.");
       }
           return commentEntities.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
    } catch(NoResultException e){
           throw new NotFoundException("리소스를 찾을 수 없습니다.");
       }
    }

//
//    public List<Comment> getNestedComments(Integer parentId) {
//        List<CommentEntity> comments = commentJpaRepository.findByParentId(parentId);
//        return comments.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
//    }
//
//    public void addComment(CommentBody commentBody) {
//        CommentEntity commentEntity = CommentMapper.INSTANCE.idAndCommentBodyToCommentEntity(null, commentBody);
//        commentJpaRepository.save(commentEntity);
//    }

}
