package com.aesopwow.echoesofaesop.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserResponseDto {
    private final String name;
    private final String email;
}
