package com.example.sixneek.article.service;

import com.example.sixneek.article.dto.ArticleResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import com.example.sixneek.like.service.LikeService;
import com.example.sixneek.member.entity.Member;
import com.example.sixneek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final LikeService likeService;

    // 기사 조회
    public List<ArticleResponseDto> getArticles(String tag,Integer size, Long lastArticleId) {

        Pageable pageable = PageRequest.of(0, size);

        Page<Article> articlesPage = null;
        if (tag != null && !tag.isEmpty()) {
            if (lastArticleId != null) {
                //태그별 기사 더 보기 조회
                articlesPage = articleRepository.findByTagAndIdLessThanOrderByIdDesc(tag, lastArticleId, pageable);
            }
        } else {
            if (lastArticleId != null) {
                //전체 기사 더 보기 조회
                articlesPage = articleRepository.findByIdLessThanOrderByIdDesc(lastArticleId, pageable);
            }
        }

        return articlesPage.stream()
                .map(article -> new ArticleResponseDto(
                        article.getId(),
                        article.getTag(),
                        article.getTitle(),
                        article.getImage(),
                        article.getDate())
                )
                .collect(Collectors.toList());
    }

    // 기사 상세조회
    public ArticleResponseDto getArticle(Long articleId, Optional<UserDetailsImpl> userDetails) {
        Article article = articleRepository.findById(articleId).orElseThrow (
                () -> new RuntimeException("기사가 존재하지 않습니다.")
        );

        boolean checkLike = false;
        if (userDetails.isPresent()) { // 회원이 접속한 경우, 좋아요 등록유무 확인.
            Member member = userDetails.get().getMember();
            checkLike = likeService.checkLike(articleId, member);
        }
        return new ArticleResponseDto(article, checkLike);
    }


}

