package com.example.backeendproject1.service.comment;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.repository.comment.CommentJpaRepository;
import com.example.backeendproject1.repository.member.MemberEntity;
import com.example.backeendproject1.repository.member.MemberJpaRepository;
import com.example.backeendproject1.repository.post.PostEntity;
import com.example.backeendproject1.repository.post.PostJpaRepository;
import com.example.backeendproject1.service.exceptions.NotAcceptException;
import com.example.backeendproject1.service.exceptions.NotFoundException;
import com.example.backeendproject1.service.mapper.CommentMapper;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import com.example.backeendproject1.web.dto.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;



        public void addCommentToPost(String postId, CommentBody commentBody) {
            Integer postIdInt = Integer.valueOf(postId);
            try {
                PostEntity post = postJpaRepository.findById(postIdInt)
                        .orElseThrow(() -> new NotFoundException("해당 포스트를 찾을 수 없습니다."));
                commentBody.setPostId(postIdInt);

                MemberEntity member = memberJpaRepository.findById(commentBody.getMemberId())
                        .orElseThrow(() -> new NotFoundException("아이디 " + commentBody.getMemberId() + "를 찾을 수 없습니다."));
                CommentEntity commentEntity = CommentMapper.INSTANCE.idAndCommentBodyToCommentEntity(member, commentBody);
                CommentEntity commentEntityCreated = commentJpaRepository.save(commentEntity);
                post.getComments().add(commentEntityCreated);
                postJpaRepository.save(post);
            } catch (NumberFormatException e) {
                // Handle the case where postId is not a valid integer
                System.out.println("Invalid postId format: " + postId);
            } catch (NotFoundException nfe) {
                // Handle the NotFoundException appropriately
                System.out.println(nfe.getMessage());
            } catch (Exception e) {
                // Handle other exceptions
                e.printStackTrace();
                System.out.println("예외");
            }
        }


//
//        } catch (DataIntegrityViolationException dive) {
//            dive.printStackTrace();
//            out.println("데이터베이스 제약조건 위반");
//        } catch (Exception e) {
//            e.printStackTrace();
//            out.println("예외");
//        }
//    }

    public List<Comment> getCommentsForPost(String postId) {
        Integer postIdInt = Integer.valueOf(postId);
        List<CommentEntity> commentEntities = commentJpaRepository.findByPostEntityPostId(postIdInt);
        if (commentEntities.isEmpty()) throw new NotFoundException("아직 댓글이 없습니다.");
        return commentEntities.stream()
                .map(CommentMapper.INSTANCE::commentEntityToComment)
                .collect(Collectors.toList());
    }


    @Transactional
    public void updateComment(String commentId, String newContent) {
        Integer commentIdInt = Integer.valueOf(commentId);
        CommentEntity commentEntity = commentJpaRepository.findById(commentIdInt).orElseThrow(() -> new NotFoundException("해당 코멘트를 찾을 수 없습니다."));
        if (commentEntity != null) {
            // Parse the JSON string to extract the "content" property
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(newContent);
                String updatedContent = jsonNode.get("content").asText();
                // Update the content of the comment entity
                commentEntity.setContent(updatedContent);
                commentJpaRepository.save(commentEntity);
            } catch (JsonProcessingException e) {
                // Handle JSON parsing exception
                // You can log an error or throw a specific exception based on your needs
                throw new RuntimeException("댓글을 업데이트하는 도중에 알 수 없는 Error가 발생했습니다. ", e);
            }
        }
    }

    @Transactional
    public void deleteComment(String commentId) {
        try {
            Integer commentIdInt = Integer.valueOf(commentId);
            commentJpaRepository.deleteById(commentIdInt);
        } catch (NumberFormatException e) {
            throw new NotAcceptException("comment id 형식이 올바르지 않습니다.");
        }
    }

//    private class idAndCommentToCommentEntity extends CommentEntity {
//        public idAndCommentToCommentEntity(Object o, CommentBody commentBody) {
//        }
//
//    private class idAndCommentBodyToCommentEntity extends CommentEntity {
//        public idAndCommentBodyToCommentEntity(Object o, CommentBody commentBody) {
//        }
//    }
}
// 모든 댓글 조회
//    public List<Comment> findAllComments() {
//        List<CommentEntity> commentEntities = commentJpaRepository.findAll();
//        if (commentEntities.isEmpty()) throw new NotFoundException("아직 댓글이 없습니다.");
//
//        return commentEntities.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
//    }

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-------------------------------

//새로운 댓글 등록
//    public Integer saveComment(CommentBody commentBody) {
//        CommentEntity commentEntity = CommentMapper.INSTANCE.idAndCommentBodyToCommentEntity(null,commentBody );
//        CommentEntity commentEntityCreated;
////포스트가 삭제됐을 때, 에러메세지 뜨는 예외 잡기..?!
//        try{
//            commentEntityCreated = commentJpaRepository.save(commentEntity);
//        }catch (DataIntegrityViolationException ex) {
//            throw new NotAcceptException("관련된 게시글을 찾을 수 없습니다 : 데이터 무결성 제약조건 위반");
//        }catch(RuntimeException exception){
//            throw new NotAcceptException("댓글을 업로드하는 도중에 알 수 없는 Error가 발생했습니다. ");
//        }
//        return commentEntityCreated.getId();
//    }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-------------------------------

//작성자 기준으로 코멘트 검색
//    public List<Comment> findCommentsByAuthor(String authorNickName) {
//       try {
//           List<CommentEntity> commentEntities = commentJpaRepository.findCommentEntitiesByAuthor(authorNickName);
//
//       if(commentEntities.isEmpty()){
//           throw new NotFoundException("아직 댓글이 없습니다.");
//       }
//           return commentEntities.stream().map(CommentMapper.INSTANCE::commentEntityToComment).collect(Collectors.toList());
//    } catch(NoResultException e){
//           throw new NotFoundException("리소스를 찾을 수 없습니다.");
//       }
//    }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-------------------------------
// 코멘트 수정
//    @Transactional
//    public Comment updateComment(String id, CommentBody commentBody) {
//        Integer idInt = Integer.valueOf(id);
//        CommentEntity commentEntityUpdated = commentJpaRepository.findById(idInt)
//                               .orElseThrow(() ->new NotFoundException("해당 Id: " + idInt + "의 Comment를 찾을 수 없습니다."));
//        commentEntityUpdated.setCommentBody(commentBody);
//        return CommentMapper.INSTANCE.commentEntityToComment(commentEntityUpdated);
//    }

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-------------------------------
//코멘트삭제
//    public void deleteComment(String id) {
//        try {
//            Integer idInt = Integer.valueOf(id);
//            commentJpaRepository.deleteById(idInt);
//        } catch (NumberFormatException e) {
//            throw new NotAcceptException("comment id 형식이 올바르지 않습니다.");
//        }
//    }