package com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto;

import com.aesopwow.echoesofaesop.data.entity.matching.MatchingComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchingCommentResponseDto {

    private Long commentId;
    private String content;
    private String userNickname;
    private float rate;
    private LocalDateTime createdAt;

    public static MatchingCommentResponseDto fromEntity(MatchingComment comment) {
        return new MatchingCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUserId().getNickName(),
                comment.getRate(),
                comment.getCreatedAt()
        );
    }
}