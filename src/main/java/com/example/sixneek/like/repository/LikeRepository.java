package com.example.sixneek.like.repository;

import com.example.sixneek.like.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<ArticleLike, Long> {
    boolean existsByArticleIdAndUserId(Long articleId, Long id);
    void deleteByArticleIdAndUserId(Long articleId, Long id);
}
