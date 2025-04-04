package com.aesopwow.echoesofaesop.respository;

import com.hugudungs.echoesofaesop.auth.repository.UserRepository;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.aesopwow.echoesofaesop.common.enums.LoginType;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByEmail() {
        // given
        User user = User.builder()
                .email("test@example.com")
                .nickName("testuser")
                .password("password")
                .loginType(LoginType.COMMON)
                .build();
        userRepository.save(user);

        // when
        User foundUser = userRepository.findByEmail("test@example.com").orElse(null);

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testFindByNickname() {
        // given
        User user = User.builder()
                .email("test2@example.com")
                .nickName("testuser2")
                .password("password")
                .loginType(LoginType.COMMON)
                .build();
        userRepository.save(user);

        // when
        User foundUser = userRepository.findByNickName("testuser2").orElse(null);

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getNickName()).isEqualTo("testuser2");
    }

    // User 생성 테스트 코드 작성해줘
    @Test
    void testCreateUser() {
        // given
        User user = User.builder()
                .email("newuser@example.com")
                .nickName("newuser")
                .password("password")
                .loginType(LoginType.COMMON)
                .build();

        // when
        User savedUser = userRepository.save(user);

        // then
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("newuser@example.com");
        assertThat(foundUser.get().getNickName()).isEqualTo("newuser");
    }
}