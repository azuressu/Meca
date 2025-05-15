package com.meca.auth.service;

import com.meca.auth.dto.SignUpRequest;
import com.meca.auth.entity.User;
import com.meca.auth.exception.UserException;
import com.meca.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest request) {
        log.info("회원가입 요청 시작 : username={}, nickname={}",
                request.getUsername(), request.getNickname());

        validateDuplicateUser(request.getUsername());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User
                .builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .password(encodedPassword)
                .build();

        log.info("회원가입 성공 : userId={}, username={}", user.getUsername(), user.getUsername());

        userRepository.save(user);
    }

    /**
     * 중복 사용자 확인
     *
     * @param username : 사용자 명이 겹치는지 확인
     */
    private void validateDuplicateUser(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserException(UserException.UserErrorType.DUPLICATED_USER);
        }
    }

}
