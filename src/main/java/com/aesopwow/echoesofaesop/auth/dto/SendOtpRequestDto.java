package com.aesopwow.echoesofaesop.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SendOtpRequestDto {
    @NotBlank
    @Email
    final String email;
}
