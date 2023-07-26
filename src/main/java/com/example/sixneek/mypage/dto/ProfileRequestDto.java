package com.example.sixneek.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDto {
    private String password;
    private String nickname;
    private Boolean isSubscribe;
}