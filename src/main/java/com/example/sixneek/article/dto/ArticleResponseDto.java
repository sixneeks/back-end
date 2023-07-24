package com.example.sixneek.article.dto;

import com.example.sixneek.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String image;
    private String title;
    private String date;
    private String tagName;
    private String content;
    private int likeCnt;
    private boolean likeCheck;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.image = article.getImage();
        this.title = article.getTitle();
        this.date = article.getDate();
        this.tagName = article.getTagName();
        this.content = article.getContent();
        this.likeCnt = article.getLikeList().size();
    }

    public ArticleResponseDto(Article article, boolean likeCheck) {
        this.id = article.getId();
        this.image = article.getImage();
        this.title = article.getTitle();
        this.date = article.getDate();
        this.tagName = article.getTagName();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeCnt = article.getLikeList().size();
        this.likeCheck = likeCheck;
    }
}
