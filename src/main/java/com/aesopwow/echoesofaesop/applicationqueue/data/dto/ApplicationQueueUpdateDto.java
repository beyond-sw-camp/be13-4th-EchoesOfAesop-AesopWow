package com.aesopwow.echoesofaesop.applicationqueue.data.dto;

import com.aesopwow.echoesofaesop.common.enums.ApplicationQueueStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationQueueUpdateDto {

    @NotBlank(message = "멘토링 신청 내용을 입력해야 합니다.")
    private String content;

    @NotNull(message = "신청 상태는 필수 입력값입니다.")
    private ApplicationQueueStatus applicationQueueStatus;
}
