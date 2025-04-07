package com.aesopwow.echoesofaesop.userInfo.dto.user;

import com.aesopwow.echoesofaesop.data.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class UserProfileResponse {
    private String username;
    private LocalDateTime createdAt;

    public static UserProfileResponse of(User user) {
        return UserProfileResponse.builder()
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
