package com.aesopwow.echoesofaesop.matching.data.dto;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import lombok.Getter;

@Getter
public class MatchingRequestDto {
    private String title;
    private String content;
    private BoardType boardType;
    private String tag;
}
