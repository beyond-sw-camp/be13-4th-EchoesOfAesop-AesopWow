package com.aesopwow.echoesofaesop.userInfo.service;

import com.aesopwow.echoesofaesop.auth.dto.UpdateUserResponseDto;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UserProfileResponseDTO;

public interface UserService {
    // 유저의 기본 정보 조회
    UserProfileResponseDTO getUserProfile(Long userId);

    // 유저의 기본 정보 수정
    UpdateUserResponseDto updateUserProfile(Long userId, UpdateUserProfileDTO updateUserProfileDTO);

    // 유저 멘토 정보 수정
    UpdateUserMentorProfileDTO updateUserMentorProfile(Long userId, UpdateUserMentorProfileDTO updateUserMentorProfileDTO);

    // 유저 멘티 정보 수정
    UpdateUserMenteeProfileDTO updateUserMenteeProfile(Long userId, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO);
}
