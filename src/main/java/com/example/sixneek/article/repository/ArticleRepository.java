package com.example.sixneek.article.repository;

import com.example.sixneek.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);
    Page<Article> findByTag(String tag, Pageable pageable);

    Page<Article> findByIdLessThanOrderByIdDesc(Long lastPostId, Pageable pageable);
    Page<Article> findByTagAndIdLessThanOrderByIdDesc(String tag, Long lastArticleId, Pageable pageable);

    Optional<Article> findArticleById(Long id);
}