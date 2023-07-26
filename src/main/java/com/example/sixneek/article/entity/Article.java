package com.example.sixneek.article.entity;


import com.example.sixneek.article.dto.ArticleRequestDto;
import com.example.sixneek.like.entity.Like;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    private String date;
    @Column(length = 20000)
    private String content;
    private String tag;

    @Builder.Default
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Like> likeList = new ArrayList<>();

    // Scheduler : 기사 업데이트
    public void updateByArticleRequestDto(ArticleRequestDto articleRequestDto) {
        this.id = articleRequestDto.getId();
        this.image = articleRequestDto.getImage();
        this.title = articleRequestDto.getTitle();
        this.date = articleRequestDto.getDate();
        this.content = articleRequestDto.getContent();
        this.tag = articleRequestDto.getTag();
    }
}
