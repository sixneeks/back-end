package com.example.sixneek.article.service;

import com.example.sixneek.article.dto.ArticleResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArticles() {
        Article article = mock(Article.class);
        LocalDateTime now = LocalDateTime.now();

        when(article.getId()).thenReturn(1L);
        when(article.getTag()).thenReturn("tag1");
        when(article.getTitle()).thenReturn("title1");
        when(article.getImage()).thenReturn("image1");
        when(article.getCreatedAt()).thenReturn(now);
        when(article.getContent()).thenReturn("content1");
        when(article.getLikesCount()).thenReturn(10L);

        Page<Article> page = new PageImpl<>(Arrays.asList(article));

        when(articleRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<ArticleResponseDto> response = articleService.getArticles(null, 0, 1);

        assertEquals(1, response.size());
        assertEquals(article.getTag(), response.get(0).getTag());
        assertEquals(article.getTitle(), response.get(0).getTitle());
        verify(articleRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testGetMoreArticles() {
        Article article1 = mock(Article.class);
        Article article2 = mock(Article.class);

        LocalDateTime now = LocalDateTime.now();

        when(article1.getId()).thenReturn(1L);
        when(article1.getTag()).thenReturn("tag1");
        when(article1.getTitle()).thenReturn("title1");
        when(article1.getImage()).thenReturn("image1");
        when(article1.getCreatedAt()).thenReturn(now);
        when(article1.getContent()).thenReturn("content1");
        when(article1.getLikesCount()).thenReturn(10L);

        when(article2.getId()).thenReturn(2L);
        when(article2.getTag()).thenReturn("tag2");
        when(article2.getTitle()).thenReturn("title2");
        when(article2.getImage()).thenReturn("image2");
        when(article2.getCreatedAt()).thenReturn(now.plusDays(1));
        when(article2.getContent()).thenReturn("content2");
        when(article2.getLikesCount()).thenReturn(20L);

        Page<Article> page = new PageImpl<>(Arrays.asList(article1, article2));

        when(articleRepository.findByIdLessThanOrderByIdDesc(anyLong(), any(Pageable.class))).thenReturn(page);

        List<ArticleResponseDto> response = articleService.getMoreArticles(null, 1L, 2);

        assertEquals(2, response.size());
        assertEquals(article1.getTag(), response.get(0).getTag());
        assertEquals(article1.getTitle(), response.get(0).getTitle());
        assertEquals(article2.getTag(), response.get(1).getTag());
        assertEquals(article2.getTitle(), response.get(1).getTitle());
        verify(articleRepository, times(1)).findByIdLessThanOrderByIdDesc(anyLong(), any(Pageable.class));
    }


    @Test
    public void testGetArticleById() {
        Article article = mock(Article.class);
        LocalDateTime now = LocalDateTime.now();

        when(article.getId()).thenReturn(1L);
        when(article.getTag()).thenReturn("tag1");
        when(article.getTitle()).thenReturn("title1");
        when(article.getImage()).thenReturn("image1");
        when(article.getCreatedAt()).thenReturn(now);
        when(article.getContent()).thenReturn("content1");
        when(article.getLikesCount()).thenReturn(10L);

        when(articleRepository.findArticleById(anyLong())).thenReturn(Optional.of(article));

        ArticleResponseDto response = articleService.getArticleById(1L);

        assertEquals(article.getTag(), response.getTag());
        assertEquals(article.getTitle(), response.getTitle());
        verify(articleRepository, times(1)).findArticleById(anyLong());
    }
}

