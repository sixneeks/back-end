package com.example.sixneek.article.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String tag;
    private String title;
    private String content;
    private String image;
    private String likeCount;

    @Builder
    private Article(String tag, String title, String content, String image, String likeCount) {
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.image = image;
        this.likeCount = likeCount;
    }
}
