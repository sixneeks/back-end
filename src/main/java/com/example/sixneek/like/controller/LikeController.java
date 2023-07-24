package com.example.sixneek.like.controller;

import com.example.sixneek.global.dto.ApiResponseDto;
import com.example.sixneek.like.service.LikeService;
import com.example.sixneek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/article/like/{articleId}")
    public ApiResponseDto saveLike(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ApiResponseDto(200, "OK", likeService.saveLike(articleId, userDetails.getUser()));
    }
}
