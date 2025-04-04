package com.aesopwow.echoesofaesop.applicationqueue.data.dto;

import com.aesopwow.echoesofaesop.data.entity.matching.ApplicationQueue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationQueueResponseDto {

    private Long applicationQueueId;
    private Long matchingId;
    private Long userId;
    private String content;
    private String status;

    // Entity → DTO 변환 메서드
    public static ApplicationQueueResponseDto fromEntity(ApplicationQueue applicationQueue) {
        return new ApplicationQueueResponseDto(
                applicationQueue.getId(),
                applicationQueue.getMatching().getId(),
                applicationQueue.getUser().getId(),
                applicationQueue.getContent(),
                applicationQueue.getApplicationQueueStatus().name()
        );
    }
}
