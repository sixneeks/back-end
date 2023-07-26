package com.example.sixneek.readed.Service;

import com.example.sixneek.article.entity.Article;
import com.example.sixneek.article.repository.ArticleRepository;
import com.example.sixneek.global.exception.ArticleNotFoundException;
import com.example.sixneek.member.entity.Member;
import com.example.sixneek.readed.dto.ReadedResponseDto;
import com.example.sixneek.readed.entity.Readed;
import com.example.sixneek.readed.repository.ReadedRepository;
import com.example.sixneek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadedService {
    private final ReadedRepository readedRepository;
    private final ArticleRepository articleRepository;

    public ReadedResponseDto postReaded(UserDetailsImpl userDetails, Long articleId) {
        Member member = userDetails.getMember();
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleNotFoundException("기사를 찾을 수 없습니다."));
        Readed readed = new Readed(member, article);
        readedRepository.save(readed);

        return new ReadedResponseDto(readed.getMember().getId(), readed.getArticle().getId());
    }
}
