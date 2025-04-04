package com.aesopwow.echoesofaesop.comment.freecomment.data.dto;

import com.aesopwow.echoesofaesop.common.enums.CommentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "댓글 생성 요청 DTO")
public class FreeCommentGenerationDto {

    @NotNull(message = "유저 ID는 필수 입력값입니다.")
    private final Long userId;

    @NotBlank(message = "댓글 유형은 필수 입력값입니다.")
    private final String commentType;

    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private final String content;

    public CommentType getCommentType() {
        try {
            return CommentType.valueOf(commentType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 댓글 타입입니다: " + commentType);
        }
    }
}
