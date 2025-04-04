package com.aesopwow.echoesofaesop.userInfo.controller;

import com.aesopwow.echoesofaesop.auth.dto.UpdateUserResponseDto;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UpdateUserProfileDTO;
import com.aesopwow.echoesofaesop.userInfo.dto.user.UserProfileResponseDTO;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface UserController {

    // 유저의 기본 정보 조회
    @Operation(summary = "유저 프로필 조회", description = "유저의 프로필을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<UserProfileResponseDTO>> getUserProfile(Long userId);

    // 유저의 기본 정보 수정
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<UpdateUserResponseDto>> updateUserProfile(Long userId, UpdateUserProfileDTO updateUserProfileDTO);

    // 유저 멘토 정보 수정
    @Operation(summary = "유저 멘토 프로필 수정", description = "유저의 멘토 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 멘토 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )

    })
    ResponseEntity<ResponseDto<UpdateUserMentorProfileDTO>> updateUserMentorProfile(Long userId, UpdateUserMentorProfileDTO updateUserMentorProfileDTO);

    // 유저 멘티 정보 수정
    @Operation(summary = "유저 멘티 프로필 수정", description = "유저의 멘티 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 멘티 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<UpdateUserMenteeProfileDTO>> updateUserMenteeProfile(Long userId, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO);
}
