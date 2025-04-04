package com.aesopwow.echoesofaesop.comment.freecomment.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "댓글 수정 요청 DTO")
public class FreeCommentUpdateDto {

    @Schema(description = "수정할 댓글 내용", example = "수정된 댓글 내용입니다.")
    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String content;
}
