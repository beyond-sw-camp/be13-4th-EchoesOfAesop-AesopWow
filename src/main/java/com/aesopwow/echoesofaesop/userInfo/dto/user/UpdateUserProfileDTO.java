package com.aesopwow.echoesofaesop.userInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileDTO {
    private String name;
    private String email;
    private String password;
}
