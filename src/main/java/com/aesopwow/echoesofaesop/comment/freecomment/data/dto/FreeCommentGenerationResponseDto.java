package com.aesopwow.echoesofaesop.comment.freecomment.data.dto;

import com.aesopwow.echoesofaesop.data.entity.comment.FreeComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreeCommentGenerationResponseDto {

    private Long id;

    private String content;

    private String userNickname;

    private String commentType;

    public static FreeCommentGenerationResponseDto fromEntity(FreeComment comment) {
        FreeCommentGenerationResponseDto dto = new FreeCommentGenerationResponseDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserNickname(comment.getUser().getNickName());
        dto.setCommentType(comment.getCommentType().name());
        return dto;
    }
}