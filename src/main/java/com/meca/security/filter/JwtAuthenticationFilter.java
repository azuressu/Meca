package com.meca.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meca.auth.dto.LoginRequest;
import com.meca.common.dto.ApiResponse;
import com.meca.common.exception.CommonException;
import com.meca.security.impl.UserDetailsImpl;
import com.meca.security.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        setFilterProcessesUrl("/api/v1/auth/sign-in");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            AuthenticationException {
        try {
            LoginRequest loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword(), null));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 아이디와 권한으로 JWT 생성
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String username = userDetails.getUsername();
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = jwtUtil.createToken(username);

        // JWT 토큰을 담을 쿠키 생성
        Cookie jwtCookie = new Cookie("Authorization", accessToken);
        jwtCookie.setMaxAge((int) jwtUtil.getExpirationTime()); // 쿠키 유효 시간
        jwtCookie.setPath("/"); // 쿠키의 적용 경로 설정 (루트 경로)
        // jwtCookie.setHttpOnly(true); // 쿠키 접근 방지 (보안 강화)

        // 생성된 쿠키를 응답에 추가
        response.addCookie(jwtCookie);

        ApiResponse<Map<String, String>> tokenResponse = new ApiResponse("로그인 성공",
                Collections.singletonMap("Authorization", "AccessToken이 쿠키에 담겼습니다.")); // 응답 본문에서 accessToken은 더이상 직접 전달하지 않음

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        throw new CommonException("로그인에 실패했습니다. 다시 시도해주세요.", "USER_008", HttpStatus.UNAUTHORIZED);
    }

}