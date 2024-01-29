package com.example.backeendproject1.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PostJpaRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findPostEntityById(Integer id);


}
