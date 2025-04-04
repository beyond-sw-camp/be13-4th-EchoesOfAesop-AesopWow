package com.aesopwow.echoesofaesop.board.notice.data.dto;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NoticeResponseDto {
    private final Long noticeId;
    private final String noticeTitle;
    private final String noticeContent;
    private final BoardType boardType;
    private final Long authorId;
    private final int noticeViews;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
}
