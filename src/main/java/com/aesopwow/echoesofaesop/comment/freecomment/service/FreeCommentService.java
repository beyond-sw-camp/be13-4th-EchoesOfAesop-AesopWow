package com.aesopwow.echoesofaesop.comment.freecomment.service;

import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;

public interface FreeCommentService {

    FreeCommentGenerationResponseDto createComment(Long freeId, FreeCommentGenerationDto requestDto);

    FreeCommentUpdateResponseDto updateComment(Long freeId, Long commentId, FreeCommentUpdateDto requestDto);

    void deleteComment(Long commentId);
}
