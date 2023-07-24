package com.example.sixneek.article.repository;

import com.example.sixneek.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    //    List<Article> findByTitleContaining(String keyword);     // title 에서만 검색
    List<Article> findByTitleContainingOrContentContaining(String title, String content);       // title & content 에서 검색
    Optional<Article> findById(Long articleId);

}
