package com.example.sixneek.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
@Getter
@NoArgsConstructor()
@Entity
public class Article {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
