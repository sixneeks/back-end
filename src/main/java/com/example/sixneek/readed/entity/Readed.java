package com.example.sixneek.readed.entity;

import com.example.sixneek.article.entity.Article;
import com.example.sixneek.member.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.relational.core.mapping.Column;

public class Readed {



    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;


}
