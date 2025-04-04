package com.aesopwow.echoesofaesop.board.free.service;

import com.aesopwow.echoesofaesop.board.free.data.dto.FreeCreateRequestDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeSearchRequestDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeSearchResponseDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreeService {

    FreeSearchResponseDto createPost(FreeCreateRequestDto requestDto);

    Page<FreeSearchResponseDto> getPosts(FreeSearchRequestDto searchRequest, Pageable pageable);

    FreeSearchResponseDto updatePost(Long id, FreeUpdateRequestDto requestDto);

    void deletePost(Long id);

    FreeSearchResponseDto getPostById(Long id);
}