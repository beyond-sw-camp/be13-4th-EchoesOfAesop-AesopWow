package com.aesopwow.echoesofaesop.userInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private Long userId;
    private String nickname;
    private String email;
    private String currentJob;
    private String desiredJob;
    private int postCount;
    private int commentCount;
    private int matchingCommentCount;
    private int matchingCount;
    private UserProfileResponse mentorProfile;
    private UserProfileResponse menteeProfile;
}


