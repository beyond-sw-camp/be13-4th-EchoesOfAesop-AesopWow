package com.aesopwow.echoesofaesop.board.free.data.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeUpdateRequestDto {
    @NotNull(message = "title은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "content는 필수 입력값입니다.")
    private String content;
}
