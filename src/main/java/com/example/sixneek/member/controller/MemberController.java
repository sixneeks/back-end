package com.example.sixneek.member.controller;

import com.example.sixneek.member.dto.MemberRequestDto;
import com.example.sixneek.member.dto.MemberResponseDto;
import com.example.sixneek.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MemberController {

    private final MemberService memberService;

    // 마이 페이지 조회
    // 헤더로 토큰을 받는다고 하셔서 일단 예상해서 적어놨습니다.
    public MemberResponseDto getMypage(@RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken,
                                       @RequestHeader(JwtUtil.REFRESH_HEADER) String refresh,
                                       HttpServletResponse response) {
        return memberService.getMypage(accessToken, refresh, response);
    }

    // 마이 페이지  닉네임 수정
    @PutMapping("")
    public MemberResponseDto updateMypage(@RequestBody MemberRequestDto requestDto, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response) {
        return memberService.updateMypage(requestDto, accessToken, refreshToken, response);
    }

}
