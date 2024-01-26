package com.example.backeendproject1.service;

import com.example.backeendproject1.repository.comment.CommentEntity;
import com.example.backeendproject1.web.dto.Comment;
import com.example.backeendproject1.web.dto.CommentBody;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T01:09:41-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentEntityToComment(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.content( commentEntity.getContent() );
        comment.author( commentEntity.getAuthor() );
        comment.postId( commentEntity.getPostId() );
        comment.id( commentEntity.getId() );
        comment.createdAt( commentEntity.getCreatedAt() );

        return comment.build();
    }

    @Override
    public CommentEntity idAndCommentBodyToCommentEntity(Integer id, CommentBody commentBody) {
        if ( id == null && commentBody == null ) {
            return null;
        }

        CommentEntity.CommentEntityBuilder commentEntity = CommentEntity.builder();

        if ( commentBody != null ) {
            commentEntity.content( commentBody.getContent() );
            commentEntity.author( commentBody.getAuthor() );
            commentEntity.postId( commentBody.getPostId() );
        }
        commentEntity.id( id );

        return commentEntity.build();
    }
}
