package com.aesopwow.echoesofaesop.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerificationOtpRequestDto {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^[0-9]+$")
    private final String otp;
}
