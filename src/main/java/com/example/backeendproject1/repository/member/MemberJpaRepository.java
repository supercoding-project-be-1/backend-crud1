package com.example.backeendproject1.repository.member;

import com.example.backeendproject1.repository.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity findByEmail(String email);
    MemberEntity findByNickname(String nickname);

}