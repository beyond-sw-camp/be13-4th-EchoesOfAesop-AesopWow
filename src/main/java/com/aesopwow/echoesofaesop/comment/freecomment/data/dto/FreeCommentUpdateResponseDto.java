package com.aesopwow.echoesofaesop.comment.freecomment.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeCommentUpdateResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private Long freeId;
}
