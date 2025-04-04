package com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchingCommentRequestDto {

    @NotNull(message = "유저 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "신청 대기열 ID는 필수입니다.")
    private Long applicationQueueId;

    @NotBlank(message = "후기 내용은 필수입니다.")
    private String content;

    @NotNull(message = "평점은 필수입니다.")
    private float rate;
}

