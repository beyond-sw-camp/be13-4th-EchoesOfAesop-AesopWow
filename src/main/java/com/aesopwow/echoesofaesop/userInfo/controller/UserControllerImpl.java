package com.aesopwow.echoesofaesop.userInfo.controller;

import com.aesopwow.echoesofaesop.auth.dto.UpdateUserResponseDto;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UserProfileResponseDTO;
import com.aesopwow.echoesofaesop.userInfo.service.UserService;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "유저 프로필", description = "유저 프로필 API")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserProfileResponseDTO>> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "유저 프로필 조회 성공",
                        true,
                        userService.getUserProfile(userId)
                ));
    }

    // 유저의 기본 정보 수정
    @Override
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto<UpdateUserResponseDto>> updateUserProfile(@PathVariable Long userId, @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "프로필 수정 성공",
                        true,
                        userService.updateUserProfile(userId, updateUserProfileDTO)
                ));
    }

    // 유저 멘토 정보 수정
    @Override
    @PutMapping("{userId}/mentor")
    public ResponseEntity<ResponseDto<UpdateUserMentorProfileDTO>> updateUserMentorProfile(@PathVariable Long userId, @RequestBody UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "멘토 프로필 수정 성공",
                        true,
                        userService.updateUserMentorProfile(userId, updateUserMentorProfileDTO)
                ));
    }

    // 유저 멘티 정보 수정
    @Override
    @PutMapping("{userId}/mentee")
    public ResponseEntity<ResponseDto<UpdateUserMenteeProfileDTO>> updateUserMenteeProfile(@PathVariable Long userId, @RequestBody UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "멘티 프로필 수정 성공",
                        true,
                        userService.updateUserMenteeProfile(userId, updateUserMenteeProfileDTO)
                ));
    }
}
