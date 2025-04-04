package com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MatchingCommentUpdateDto {

    @NotBlank(message = "후기 내용은 필수입니다.")
    private String content;

    @NotNull(message = "평점은 필수입니다.")
    private float rate;
}