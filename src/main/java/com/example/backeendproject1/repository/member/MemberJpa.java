package com.example.backeendproject1.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpa extends JpaRepository<Member, String> {
}
