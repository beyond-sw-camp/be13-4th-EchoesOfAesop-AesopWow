package com.aesopwow.echoesofaesop.auth.service;

import com.aesopwow.echoesofaesop.auth.dto.SignUpRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.TokenResponseDto;
import com.aesopwow.echoesofaesop.auth.dto.VerificationOtpRequestDto;

public interface AuthService {
    boolean hasUserByEmail(String email);

    boolean hasUserByNickname(String nickname);

    void sendOtp(String email);

    boolean checkOtpValidity(VerificationOtpRequestDto verificationOtpRequestDto);

    void createUser(SignUpRequestDto signUpRequestDto);

    TokenResponseDto login(String email, String password);

    void logout(String bearerToken);

    TokenResponseDto refresh(String bearerToken);
}
