package com.meca.auth.controller;

import com.meca.auth.dto.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 API", description = "회원 API 정리")
public interface AuthApi {

    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> signUp(@RequestBody SignUpRequest request);

    @Operation(summary = "로그아웃", description = "로그아웃 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "로그아웃 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> logout(HttpServletResponse response);
}