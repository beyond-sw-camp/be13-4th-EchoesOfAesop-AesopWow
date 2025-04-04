package com.aesopwow.echoesofaesop.service;

import com.aesopwow.echoesofaesop.auth.service.AuthService;
import com.aesopwow.echoesofaesop.common.enums.LoginType;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void findByUserEmailTestGetExistEmail() {
        User user = User.builder()
                .email("test@example.com")
                .nickName("testuser")
                .password("password")
                .loginType(LoginType.COMMON)
                .build();
        userRepository.save(user);

        boolean result = authService.findUserByEmail("test@example.com");
        assertThat(result).isTrue();
    }

    @Test
    void findByUserEmailTestGetNotExistEmail() {
        boolean result = authService.findUserByEmail("test@example.com");
        assertThat(result).isFalse();
    }

    @Test
    void findByNickNameTestGetExistNickName() {
        User user = User.builder()
                .email("test@example.com")
                .nickName("testuser")
                .password("password")
                .loginType(LoginType.COMMON)
                .build();
        userRepository.save(user);

        boolean result = authService.findUserByNickname("testuser");
        assertThat(result).isTrue();
    }

    @Test
    void findByNickNameTestGetNotExistNickName() {
        boolean result = authService.findUserByNickname("testuser");
        assertThat(result).isFalse();
    }
}
