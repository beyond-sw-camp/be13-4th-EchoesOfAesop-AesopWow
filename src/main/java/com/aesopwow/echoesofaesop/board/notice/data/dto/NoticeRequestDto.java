package com.aesopwow.echoesofaesop.board.notice.data.dto;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import lombok.Getter;

@Getter
public class NoticeRequestDto {
    private String title;
    private String content;
    private BoardType boardType;
}
