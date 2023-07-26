package com.example.sixneek.mypage.controller;

import com.example.sixneek.mypage.dto.ProfileResponseDto;
import com.example.sixneek.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 프로필(페이지에 닉네임, 아이디, 좋았슴 뜨도록)과 마이 페이지(정보 출력 및 수정 기능)

@Slf4j
@RestController
@RequiredArgsConstructor
class MypageController {

    private final MypageService mypageService;

    // 마이 페이지 간략 정보 조회
    @GetMapping("/api/members/profile")
    public ProfileResponseDto getMyProfile() {
        //TODO: 토큰을 받아와서 이메일 정보를 추출하도록 수정 필요
        return mypageService.getMyProfile();
    }

    // 프로필 설정 페이지 조회
    @GetMapping("/api/members/profile/setting")
    public ProfileResponseDto getMyProfileSetting() {
        //TODO: 토큰을 받아와서 이메일 정보를 추출하도록 수정 필요
        return mypageService.getMyProfileSetting();
    }
}