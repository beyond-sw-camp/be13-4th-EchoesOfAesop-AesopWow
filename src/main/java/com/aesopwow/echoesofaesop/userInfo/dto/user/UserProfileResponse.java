package com.aesopwow.echoesofaesop.userInfo.dto.user;

import com.aesopwow.echoesofaesop.common.enums.ProfileType;
import com.aesopwow.echoesofaesop.data.entity.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserProfileResponse {
    private float rate;
    private ProfileType profileType;
    private String introduction;
    private String experience;

    public static UserProfileResponse of(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .rate(userProfile.getRate())
                .profileType(userProfile.getProfileType())
                .introduction(userProfile.getIntroduction())
                .experience(userProfile.getExperience())
                .build();
    }
}
