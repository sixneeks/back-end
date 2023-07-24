package com.example.sixneek.article.dto;

import com.example.sixneek.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter

public class ArticleResponseDto {

    private Long id;
    private final String tag;
    private final String title;
    private final String image;
    private final LocalDateTime date;
    private String content;
    private Long likesCount;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.tag = article.getTag();
        this.title = article.getTitle();
        this.image = article.getImage();
        this.date =  article.getCreatedAt();
        this.content = article.getContent();
        this.likesCount = article.getLikeCount();
    }

    public ArticleResponseDto(String tag, String title, String image, LocalDateTime createdAt) {
        this.tag = tag;
        this.title = title;
        this.image = image;
        this.date = createdAt;
    }
}
