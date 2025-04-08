package com.aesopwow.echoesofaesop.auth.service;

import com.aesopwow.echoesofaesop.auth.dto.SignUpRequestDto;
import com.aesopwow.echoesofaesop.auth.dto.TokenResponseDto;
import com.aesopwow.echoesofaesop.auth.dto.VerificationOtpRequestDto;

public interface AuthService {
    boolean hasUserByUsername(String username);

    void createUser(SignUpRequestDto signUpRequestDto);

    TokenResponseDto login(String username, String password);

    void logout(String bearerToken);

    TokenResponseDto refresh(String bearerToken);
}
