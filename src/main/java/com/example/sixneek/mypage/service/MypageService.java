package com.example.sixneek.mypage.service;

import com.example.sixneek.member.entity.Member;
import com.example.sixneek.member.repository.MemberRepository;
import com.example.sixneek.mypage.dto.ProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 데이터베이스 연동, 예외 처리, 인증 및 권한 검사 등의 기능을 추가
@Service
@RequiredArgsConstructor
public class MypageService {

    private final MemberRepository memberRepository;

    // 닉네임, 이메일, 읽었음 개수, 좋았슴 개수
    public ProfileResponseDto getMyProfile() {
        String email = "test@test.com";
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("해당하는 회원이 존재하지 않습니다."));

        return ProfileResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .readedCount(member.getReadedList().size())
                .likesCount(member.getLikeList().size())
                .build();
    }

    // 닉네임, 출생년도, 성별, (배송지), 이모지, 직업, 관심분야, 이메일, (시사 뉴스레터 구독여부) 조회
    public ProfileResponseDto getMyProfileSetting() {
        String email = "test@test.com";
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("해당하는 회원이 존재하지 않습니다."));

        return new ProfileResponseDto(member);
    }
}
