package com.aesopwow.echoesofaesop.userInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateUserMentorProfileDTO {
    private String currentJob;
    private String introduction;
    private String experience;
}
