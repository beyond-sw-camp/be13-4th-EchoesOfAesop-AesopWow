package com.aesopwow.echoesofaesop.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}
