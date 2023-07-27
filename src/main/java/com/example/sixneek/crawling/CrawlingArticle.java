package com.example.sixneek.crawling;

import com.example.sixneek.article.dto.ArticleListResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j(topic = "Scheduler")
@RequiredArgsConstructor
public class CrawlingArticle {
    private final ArticleRepository articleRepository;

    // 크롤링: 전체 기사 조회
    @Scheduled(cron = "0 0 */2 * * *")      // 2시간 마다 기사 업데이트
    public List<ArticleListResponseDto> saveArticleList() throws IOException {
        articleRepository.deleteAll();
        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();
        long currentId = 1;
        for (int i = 1; i <= 10; i++) {
            String Article_URL = "https://www.hani.co.kr/arti/list" + i + ".html";
            articleResponseDtoList.addAll(processUrl(Article_URL, currentId));
            currentId += 15;
        }

        log.info("뉴스 기사 크롤링 완료");

        return articleResponseDtoList;
    }

    // Url 처리하기
    private List<ArticleListResponseDto> processUrl(String url, long currentId) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements contentList = document.select("div div.section-list-area div.list");

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Element contents : contentList) {
            String image = contents.select("a img").attr("abs:src");        // 이미지
            String title = contents.select("h4 a").text();      // 제목
            String date = contents.select("p span").text().substring(0, 10).replaceAll("-", "/");       // 날짜
            String tag = contents.select("strong a").text();        // 섹션(태그)
            
            String articleLink = contents.select("h4 a").attr("abs:href");      // 내용
            Document articleDocument = Jsoup.connect(articleLink).get();
            String content = articleDocument.select("div div.text").text();

            Article article = Article.builder()
                    .id(currentId++)
                    .image(image)
                    .title(title)
                    .tag(tag)
                    .date(date)
                    .content(content)
                    .build();

            articleRepository.save(article);
            articleResponseDtoList.add(new ArticleListResponseDto(article));
        }

        return articleResponseDtoList;
    }

    // 검색 기능
    public List<ArticleListResponseDto> searchKeyword(String keyword) {
        List<Article> articleList = articleRepository.findByTitleContainingOrContentContaining(keyword, keyword);       // title & content 에서 검색

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article : articleList) {
            articleResponseDtoList.add(new ArticleListResponseDto(article));
        }

        return articleResponseDtoList;
    }
}
