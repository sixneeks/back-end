package com.example.sixneek.article.dto;

import com.example.sixneek.article.entity.Article;
import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private Long id;
    private String image;
    private String title;
    private String date;
    private String content;
    private String tag;

    public ArticleRequestDto(Article article) {
        this.id = article.getId();
        this.image = article.getImage();
        this.title = article.getTitle();
        this.date = article.getDate();
        this.content = article.getContent();
        this.tag = article.getTag();
    }
}
