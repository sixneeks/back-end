package com.example.sixneek.security.jwt;

import com.example.sixneek.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        String accessToken = jwtUtil.substringToken(bearerToken);

        if (StringUtils.hasText(accessToken)) { // access 토큰이 있으면
            switch (jwtUtil.validateToken(accessToken)) { // 검증
                case DENIED -> {
                    throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
                }

                case EXPIRED -> {
                    bearerToken = request.getHeader(JwtUtil.REFRESH_HEADER);
                    String refreshToken = jwtUtil.substringToken(bearerToken);
                    if (StringUtils.hasText(refreshToken)) {
                        JwtUtil.JwtCode jwtCode = jwtUtil.validateToken(refreshToken);
                        switch (jwtCode) {
                            case EXPIRED, DENIED -> {
                                String pre = (jwtCode == JwtUtil.JwtCode.DENIED) ? "유효하지 않은" : "만료된";
                                throw new IllegalArgumentException(pre + " 토큰 입니다.");
                            }
                            case ACCESS -> {
                                String email = jwtUtil.getUserInfoFromToken(refreshToken); // 토큰 정보 추출
                                try {
                                    setAuthentication(email); // 사용자 정보 저장
                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                    return;
                                }

                                // access 재발급
                                accessToken = jwtUtil.createAccessToken(email);
                                response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
                                return;
                            }
                        }
                    }
                }
            }

            // 유효한 경우
            String email = jwtUtil.getUserInfoFromToken(accessToken); // 토큰 정보 추출
            try {
                setAuthentication(email); // 사용자 정보 저장
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}