package com.aesopwow.echoesofaesop.board.free.controller;

import com.aesopwow.echoesofaesop.board.free.data.dto.FreeCreateRequestDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeSearchRequestDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeSearchResponseDto;
import com.aesopwow.echoesofaesop.board.free.data.dto.FreeUpdateRequestDto;
import com.aesopwow.echoesofaesop.board.free.service.FreeService;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FreeControllerImpl implements FreeController {

    private final FreeService freeService;

    @Override
    public ResponseEntity<ResponseDto<FreeSearchResponseDto>> createPost(FreeCreateRequestDto requestDto) {
        FreeSearchResponseDto createdPost = freeService.createPost(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        HttpStatus.CREATED.value(),
                        "게시글이 성공적으로 생성되었습니다.",
                        true,
                        createdPost
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<Page<FreeSearchResponseDto>>> getPosts(FreeSearchRequestDto searchRequest, Pageable pageable) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "게시글 목록 조회 성공",
                        true,
                        freeService.getPosts(searchRequest, pageable)
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<FreeSearchResponseDto>> updatePost(Long id, FreeUpdateRequestDto requestDto) {
        FreeSearchResponseDto updatedPost = freeService.updatePost(id, requestDto);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "게시글이 성공적으로 수정되었습니다.",
                        true,
                        updatedPost
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> deletePost(Long id) {
        freeService.deletePost(id);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "게시글이 성공적으로 삭제되었습니다.",
                        true,
                        null
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<FreeSearchResponseDto>> getPostById(Long id) {
        FreeSearchResponseDto post = freeService.getPostById(id);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "게시글 조회 성공",
                        true,
                        post
                ));
    }
}