package com.aesopwow.echoesofaesop.matching.data.dto;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MatchingResponseDto {
    private final Long matchingId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final BoardType boardType;
    private final String matchingContent;
    private final String matchingTitle;
    private final int matchingViews;
    private final String tag;
    private final Long authorId;
}
