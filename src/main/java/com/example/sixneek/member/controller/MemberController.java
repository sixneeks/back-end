package com.example.sixneek.member.controller;

import com.example.sixneek.ApiResponseDto;
import com.example.sixneek.member.dto.SignupRequestDto;
import com.example.sixneek.member.service.MemberService;
import com.example.sixneek.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ApiResponseDto<?> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return memberService.signup(requestDto);
    }

    @GetMapping("/test")
    public String test(@RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        String accessToken = jwtUtil.substringToken(token);
        return jwtUtil.getUserInfoFromToken(accessToken);
    }

    @GetMapping("/test2")
    public String test() {
        return "test";
    }
}
