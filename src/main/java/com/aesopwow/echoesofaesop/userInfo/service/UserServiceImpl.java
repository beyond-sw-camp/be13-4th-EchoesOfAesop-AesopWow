package com.aesopwow.echoesofaesop.userInfo.service;

import com.aesopwow.echoesofaesop.auth.dto.UpdateUserResponseDto;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UserProfileResponseDTO;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 정보 조회
    @Override
    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        return UserProfileResponseDTO.builder()
                .userId(user.getId())
                .userName(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // 유저 기본 프로필 수정
    @Override
    public UpdateUserResponseDto updateUserProfile(Long userId, UpdateUserProfileDTO updateUserProfileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        user.setPassword(passwordEncoder.encode(updateUserProfileDTO.getPassword()));
        userRepository.saveAndFlush(user);

        return new UpdateUserResponseDto(user.getUsername());
    }
}
