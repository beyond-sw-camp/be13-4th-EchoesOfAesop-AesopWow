package com.aesopwow.echoesofaesop.board.free.data.dto;

import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.aesopwow.echoesofaesop.common.enums.BoardType;
import com.aesopwow.echoesofaesop.data.entity.board.Free;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FreeSearchResponseDto {
    private Long id;
    private BoardType boardType;
    private String title;
    private String content;
    private String userNickname;
    private int views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<FreeCommentGenerationResponseDto> comments;

    public static FreeSearchResponseDto fromEntity(Free free) {
        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getAuthor().getNickName())
                .boardType(free.getBoardType())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .build();
    }

    public static FreeSearchResponseDto fromEntity(
            Free free,
            List<FreeCommentGenerationResponseDto> commentDtos
    ) {
        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .boardType(free.getBoardType())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getAuthor().getNickName())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .comments(commentDtos)
                .build();
    }
}
