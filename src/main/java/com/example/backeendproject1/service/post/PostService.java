package com.example.backeendproject1.service.post;

import com.example.backeendproject1.repository.post.PostEntity;

import com.example.backeendproject1.repository.post.PostJpaRepository;
import com.example.backeendproject1.service.exceptions.NotFoundException;
import com.example.backeendproject1.service.mapper.PostMapper;
import com.example.backeendproject1.web.dto.Post;
import com.example.backeendproject1.web.dto.PostBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.backeendproject1.service.mapper.PostMapper.INSTANCE;



@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository postJpaRepository;
    public List<Post> findAllPosts() {
        List<PostEntity> postEntities = postJpaRepository.findAll();
        if (postEntities.isEmpty()) throw new NotFoundException("아직 게시글이 없습니다.");
        return postEntities.stream().map(INSTANCE::postEntityToPostDto).collect(Collectors.toList());
    }

    //trycatch있는 savePost
//    public Integer savePost(PostBody postBody) {
//        //dto => entity
//        //❓ idAndPostBodyToPostEntity는 postBody랑 postEntity를 이어주는거지?
//        //우리는 postBody만 저장하는거?
//        PostEntity postEntity = PostMapper.INSTANCE.idAndPostBodyToPostEntity(null, postBody);
//        PostEntity postEntityCreated;
//        //❓ postJpaRepository에 save라는 메소드가 있어? 없는뎅
////        postEntityCreated= postJpaRepository.save(postEntity);
////        return postEntityCreated.getId();
//
//        try {
//            //❓두 코드 모두 PostEntity를 만드는건데
//            //하나는 mapper에서 => dto와 관련있을 때
//            //하나는 repository에서 => 새로 repository에 저장하거나 repository에서 찾아올 때 등 repository와 관련된 method
//            postEntityCreated = postJpaRepository.save(postEntity);
//        } catch (DataIntegrityViolationException ex) { //DB조건에 맞지 않는 값 넣을 때 발생
//            throw new NotAcceptException("Not Accept Exception: 데이터 무결성 제약 조건에 위배, DB조건에 안 맞는다는 뜻");
//        } catch (RuntimeException exception) {
//            throw new NotAcceptException("Not Accept Exception: Post를 save하는 도중에 Error발생");
//        }
//            return postEntityCreated.getId();
//
////        PostEntity postEntity = INSTANCE.idAndPostBodyToPostEntity(null, postBody);
////        return postJpaRepository.saveItem(postEntity);
//        }

    public Integer savePost(PostBody postBody) {
        PostEntity postEntity = PostMapper.INSTANCE.idAndPostBodyToPostEntity(null, postBody);

        PostEntity postEntityCreated = postJpaRepository.save(postEntity);
        return postEntityCreated.getId();

    }

    @Transactional(transactionManager = "tmJpa")
    public Post updatePost (Integer postId, PostBody postBody){
        //❓왜 id가 integer인거야 string인거야..?
        PostEntity postEntity = postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("아이디" + postId + "를 가진 게시글을 찾지 못했습니다."));
//        postEntity.setPostBody(postBody);
        postEntity.setAuthor(postBody.getAuthor());
        postEntity.setTitle(postBody.getTitle()); //postEntity의 setPostBody한번에 하는 setter 지우고 각각 받아오기로
        postEntity.setContent(postBody.getContent());
//        postEntity.setCreatedAt(LocalDateTime.now());
        PostEntity postEntityUpdated = postJpaRepository.save(postEntity);
        return PostMapper.INSTANCE.postEntityToPostDto(postEntityUpdated);

    }

    public void deletePost (Integer postId){ //❓왜 id가 integer인거야 string인거야
//        Integer idInt= Integer.parseInt(postId); //❓왜 id를 int로 바꾸는거야 integer로 하면 안돼?
        postJpaRepository.deleteById(postId);
    }

    public Post findPostById(Integer postId){
        PostEntity postEntity = postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("아이디" + postId + "를 가진 게시글을 찾지 못했습니다."));
        return PostMapper.INSTANCE.postEntityToPostDto(postEntity);
    }
}
