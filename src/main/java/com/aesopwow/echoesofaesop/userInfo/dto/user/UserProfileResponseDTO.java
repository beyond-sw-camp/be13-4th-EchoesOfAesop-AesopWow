package com.aesopwow.echoesofaesop.userInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private Long userId;
    private String userName;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}