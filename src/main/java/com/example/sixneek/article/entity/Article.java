package com.example.sixneek.article.entity;

import com.example.sixneek.time.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;
    private String title;
    private String content;
    private String image;
    private Long likeCount;

    @Builder
    private Article(String tag, String title, String content, String image, Long likeCount) {
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.image = image;
        this.likeCount = likeCount;
    }
}
