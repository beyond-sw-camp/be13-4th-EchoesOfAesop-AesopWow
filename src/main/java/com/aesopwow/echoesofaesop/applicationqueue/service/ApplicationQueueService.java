package com.aesopwow.echoesofaesop.applicationqueue.service;

import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueRequestDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueResponseDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueUpdateDto;

import java.util.List;

public interface ApplicationQueueService {

    ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueRequestDto requestDto);

    List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId);

    List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId);

    ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto);

    void deleteApplication(Long applicationQueueId);
}