package com.example.sixneek.mypage.controller;

import com.example.sixneek.global.dto.ApiResponseDto;
import com.example.sixneek.mypage.dto.ProfileRequestDto;
import com.example.sixneek.mypage.dto.ProfileResponseDto;
import com.example.sixneek.mypage.service.MypageService;
import com.example.sixneek.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// 프로필(페이지에 닉네임, 아이디, 좋았슴 뜨도록)과 마이 페이지(정보 출력 및 수정 기능)

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/profile")
class MypageController {

    private final MypageService mypageService;

    // TODO: 토큰에서 사용자 정보 가져오는 걸로 변경하기
    // @AuthenticationPrincipal UserDetailsImpl userDetails
    // userDetails.getMember()

    // 마이 페이지 간략 정보 조회
    @GetMapping
    public ProfileResponseDto getMyProfile() {
        return mypageService.getMyProfile();
    }

    // 프로필 설정 페이지 조회
    @GetMapping("/setting")
    public ProfileResponseDto getMyProfileSetting() {
        return mypageService.getMyProfileSetting();
    }

    // /api/members/profile/setting?tab=nickname
    @PutMapping("/setting")
    public ApiResponseDto<?> updateProfile(@RequestParam String tab,
                                           @RequestBody ProfileRequestDto requestDto) {
        return mypageService.updateProfile(tab, requestDto);
    }

}