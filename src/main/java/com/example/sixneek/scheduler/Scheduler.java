package com.example.sixneek.scheduler;

import com.example.sixneek.article.dto.ArticleRequestDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import com.example.sixneek.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @Scheduled(cron = "0 0 */3 * * *")      // 3시간 마다 기사 업데이트
    public void updatePrice() {
        log.info("뉴스 기사 업데이트 실행");
        List<Article> articleList = articleRepository.findAll();
        for (Article article : articleList) {

            // 제목으로 기사 검색
            String title = article.getTitle();
            List<ArticleRequestDto> articleRequestDtoList = articleService.searchArticles(title);

            // 업데이트
            if (articleRequestDtoList.size() > 0) {
                ArticleRequestDto articleRequestDto = articleRequestDtoList.get(0);
                Long id = article.getId();
                articleService.updateBySearch(id, articleRequestDto);
            }
        }
        log.info("뉴스 기사 업데이트 마침");
    }

}