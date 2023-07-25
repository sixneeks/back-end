package com.example.sixneek.member.entity;

import com.example.sixneek.like.entity.Like;
import com.example.sixneek.readed.entity.Readed;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String birth;

    @Column
    private String gender;

    @Column
    private String emoji;

    @Column
    private String interests;

    @Column
    private String job;

    @OneToMany(mappedBy = "member")
    private List<Readed> readedList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likeList = new ArrayList<>();

    public Member(String email, String password, String nickname, String birth, String gender, String emoji, String interests, String job) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.emoji = emoji;
        this.interests = interests;
        this.job = job;
    }

    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}