package com.meca.auth.controller;

import com.meca.auth.dto.SignUpRequest;
import com.meca.auth.service.AuthService;
import com.meca.common.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi{

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest request) {
        authService.signUp(request);
        return ResponseEntity.ok(new ApiResponse<>("success", "success"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletResponse response) {
        log.info("로그아웃 요청");

        // JWT 쿠키 삭제
        ResponseCookie cookie = ResponseCookie.from("Authorization", "")
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        // ApiResponse 객체 생성 및 로깅
        ApiResponse apiResponse = new ApiResponse("success", "로그아웃 성공");
        log.info("응답 데이터: {}", apiResponse);

        // Content-Type 명시적 설정
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiResponse);
    }

}
