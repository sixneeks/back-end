package com.example.sixneek.member.service;

import com.example.sixneek.ApiResponseDto;
import com.example.sixneek.member.dto.SignupRequestDto;
import com.example.sixneek.member.entity.Member;
import com.example.sixneek.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponseDto<?> signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        // 이메일 중복 확인
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        // 닉네임 중복 확인
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        return ApiResponseDto.builder()
                .status(201)
                .message("회원가입 성공")
                .data(null)
                .build();
    }
}
