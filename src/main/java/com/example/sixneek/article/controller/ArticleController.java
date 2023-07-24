package com.example.sixneek.article.controller;

import com.example.sixneek.article.service.ArticleService;
import com.example.sixneek.crawling.CrawlingArticle;
import com.example.sixneek.global.dto.ApiResponseDto;
import com.example.sixneek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CrawlingArticle crawlingArticle;

    // 전체 기사 조회
    @GetMapping("/api/articles")
    public ApiResponseDto saveArticleList() throws IOException {
        return new ApiResponseDto(200, "OK", crawlingArticle.saveArticleList());
    }

    // 섹터별 기사 조회
    @GetMapping("/api/articles/tags/{tagName}")
    public ApiResponseDto saveArticle(@PathVariable String tagName) {
        return new ApiResponseDto(200, "OK", crawlingArticle.saveArticle(tagName));
    }

    // 검색 기능
    @GetMapping("/api/articles/search")
    public ApiResponseDto searchKeyword(@RequestParam("keyword") String keyword) {
        return new ApiResponseDto(200, "OK", crawlingArticle.searchKeyword(keyword));
    }

    // 게시글 선택 조회
    @GetMapping("/api/article/{articleId}")
    public ApiResponseDto getBoard(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return new ApiResponseDto(200, "OK", articleService.getArticle(articleId, userDetails.getUser()));      // 회원
        }

        return new ApiResponseDto(200, "OK", articleService.getArticle(articleId));         // 비회원
    }
}
