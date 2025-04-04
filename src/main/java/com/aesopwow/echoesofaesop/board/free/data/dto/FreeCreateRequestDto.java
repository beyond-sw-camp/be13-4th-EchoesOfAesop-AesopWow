package com.aesopwow.echoesofaesop.board.free.data.dto;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeCreateRequestDto {

    @NotBlank(message = "title은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "content는 필수 입력값입니다.")
    private String content;

    private BoardType boardType;

    @NotNull(message = "userId는 필수 입력값입니다.")
    private Long userId;
}
