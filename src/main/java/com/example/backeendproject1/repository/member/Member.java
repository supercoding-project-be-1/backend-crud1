package com.example.backeendproject1.repository.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "member")
public class Member {
    @Id
    @Column(name= "email")
    private String email;

    @Column(name= "password")
    private String password;
}
