package com.example.sixneek.article.controller;

import com.example.sixneek.article.dto.ArticleResponseDto;
import com.example.sixneek.article.service.ArticleService;
import com.example.sixneek.global.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ApiResponseDto<List<ArticleResponseDto>> getArticles(@RequestParam(value = "tag", required = false) String tag,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ArticleResponseDto> articles = articleService.getArticles(tag, page, size);
        return new ApiResponseDto<>(HttpStatus.OK, "기사 조회 성공", articles);
    }
    @GetMapping()
    public ApiResponseDto<List<ArticleResponseDto>> getMoreArticles(@RequestParam(value = "tag", required = false) String tag,
                                                                    @RequestParam Long lastArticleId,
                                                                    @RequestParam int size) {
        List<ArticleResponseDto> articles = articleService.getMoreArticles(tag, lastArticleId, size);
        return new ApiResponseDto<>(HttpStatus.OK, "기사 더보기 성공", articles);
    }
    @GetMapping("/{id}")
    public ApiResponseDto<ArticleResponseDto> getArticleDetail(@PathVariable Long id) {
        ArticleResponseDto responseDto = articleService.getArticleById(id);
        return new ApiResponseDto<>(HttpStatus.OK, "기사 상세조회 성공", responseDto);
    }


}
