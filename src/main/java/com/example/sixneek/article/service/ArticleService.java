package com.example.sixneek.article.service;

import com.example.sixneek.article.dto.ArticleResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import com.example.sixneek.like.service.LikeService;
import com.example.sixneek.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final LikeService likeService;

    // 게시글 선택 조회 - 회원
    public ArticleResponseDto getArticle(Long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow (
                () -> new RuntimeException("기사가 존재하지 않습니다.")
        );

        return new ArticleResponseDto(article, likeService.checkLike(article.getId(), user));
    }

    // 게시글 선택 조회 - 비회원
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow (
                () -> new RuntimeException("기사가 존재하지 않습니다.")
        );

        return new ArticleResponseDto(article);
    }
}
