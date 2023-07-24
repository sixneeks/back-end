package com.example.sixneek.article.service;

import com.example.sixneek.article.dto.ArticleResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import com.example.sixneek.global.dto.exception.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    //하나로 합쳐보기

    //기사 조회
    public List<ArticleResponseDto> getArticles(String tag, Integer page, Integer size, Long lastPostId) {
        Pageable pageable;

        if (lastPostId != null && page == null) {
            pageable = PageRequest.of(0, size);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Article> articlesPage;

        if (tag != null && !tag.isEmpty()) {
            if (lastPostId != null) {
                // 태그별 조회
                articlesPage = articleRepository.findByTagAndIdLessThanOrderByIdDesc(tag, lastPostId, pageable);
            } else {
                //태그별 조회
                articlesPage = articleRepository.findByTag(tag, pageable);
            }
        } else {
            if (lastPostId != null) {
                // 전체 조회
                articlesPage = articleRepository.findByIdLessThanOrderByIdDesc(lastPostId, pageable);
            } else {
                //전체 조회
                articlesPage = articleRepository.findAll(pageable);
            }
        }

        return articlesPage.stream()
                .map(article -> new ArticleResponseDto(article.getTag(),
                        article.getTitle(),
                        article.getImage(),
                        article.getCreatedAt()))
                .collect(Collectors.toList());
    }


    //개별 기사 상세조회
    public ArticleResponseDto getArticleById(Long id) {
       Article article = articleRepository.findArticleById(id).orElseThrow(() ->
               new ArticleNotFoundException("기사를 찾을 수 없습니다."));
       return new ArticleResponseDto(article);
    }


}
