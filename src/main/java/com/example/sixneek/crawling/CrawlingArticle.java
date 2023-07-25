package com.example.sixneek.crawling;

import com.example.sixneek.article.dto.ArticleListResponseDto;
import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingArticle {
    private final ArticleRepository articleRepository;

    // 전체 기사 조회
    public List<ArticleListResponseDto> saveArticleList() throws IOException {
        String Article_URL1 = "https://www.hani.co.kr/arti/list1.html";
        String Article_URL2 = "https://www.hani.co.kr/arti/list2.html";

        articleRepository.deleteAll();

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        articleResponseDtoList.addAll(processUrl(Article_URL1));
        articleResponseDtoList.addAll(processUrl(Article_URL2));

        return articleResponseDtoList;
    }

    // Url 처리하기
    private List<ArticleListResponseDto> processUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements contentList = document.select("div div.section-list-area div.list");

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        Long currentId = 1L;        // 시작 id 값

        List<Article> articleList = articleRepository.findAll();
        if (!articleList.isEmpty()) {
            currentId = 16L;
        }

        for (Element contents : contentList) {
            String image = contents.select("a img").attr("abs:src");        // 이미지
            String title = contents.select("h4 a").text();      // 제목
            String date = contents.select("p span").text().substring(0, 10).replaceAll("-", "/");       // 날짜
            String tagName = contents.select("strong a").text();        // 섹션(태그)
            
            String articleLink = contents.select("h4 a").attr("abs:href");      // 내용
            Document articleDocument = Jsoup.connect(articleLink).get();
            String content = articleDocument.select("div div.text").text();

            Article article = new Article();
            article.setId(currentId);
            article.setImage(image);
            article.setTitle(title);
            article.setDate(date);
            article.setTagName(tagName);
            article.setContent(content);

            articleResponseDtoList.add(new ArticleListResponseDto(article));
            articleRepository.save(article);

            currentId++;
        }

        return articleResponseDtoList;
    }

    // 섹터별 기사 조회
    public List<ArticleListResponseDto> getTagName(String tagName) {
        List<Article> articleList = articleRepository.findAll();

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article : articleList) {
            if (article.getTagName().equals(tagName)) {
                articleResponseDtoList.add(new ArticleListResponseDto(article));
            }
        }

        return articleResponseDtoList;
    }

    // 검색 기능
    public List<ArticleListResponseDto> searchKeyword(String keyword) {
//        List<Article> articleList = articleRepository.findByTitleContaining(keyword);     // title 에서만 검색
        List<Article> articleList = articleRepository.findByTitleContainingOrContentContaining(keyword, keyword);       // title & content 에서 검색

        List<ArticleListResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article : articleList) {
            articleResponseDtoList.add(new ArticleListResponseDto(article));
        }

        return articleResponseDtoList;
    }
}
