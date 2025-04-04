package com.aesopwow.echoesofaesop.matching.service;

import com.aesopwow.echoesofaesop.matching.data.dto.MatchingRequestDto;
import com.aesopwow.echoesofaesop.matching.data.dto.MatchingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchingService {
    MatchingResponseDto createMatching(MatchingRequestDto requestDto) throws Exception;

    MatchingResponseDto updateMatching(Long matchingId, MatchingRequestDto requestDto) throws Exception;

    void deleteMatching(Long matchingId) throws Exception;

    Page<MatchingResponseDto> getMatchingPosts(Pageable pageable) throws Exception;

    MatchingResponseDto getMatchingById(Long matchingId) throws Exception;
}
