package com.aesopwow.echoesofaesop.comment.freecomment.controller;

import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;
import com.aesopwow.echoesofaesop.comment.freecomment.service.FreeCommentService;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FreeCommentControllerImpl implements FreeCommentController {

    private final FreeCommentService freeCommentService;

    public ResponseEntity<ResponseDto<FreeCommentGenerationResponseDto>> createComment(
            @PathVariable Long freeId, @RequestBody FreeCommentGenerationDto requestDto) {
        FreeCommentGenerationResponseDto createdComment = freeCommentService.createComment(freeId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "댓글 생성 성공",
                                true,
                                createdComment
                        ));
    }

    public ResponseEntity<ResponseDto<FreeCommentUpdateResponseDto>> updateComment(
            @PathVariable Long freeId, @PathVariable Long commentId, @RequestBody FreeCommentUpdateDto requestDto) {
        FreeCommentUpdateResponseDto updatedComment = freeCommentService.updateComment(freeId, commentId, requestDto);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "댓글 수정 성공",
                        true,
                        updatedComment
                ));
    }

    public ResponseEntity<ResponseDto<Void>> deleteComment(@PathVariable Long commentId) {
        freeCommentService.deleteComment(commentId);

        return ResponseEntity.ok(
                        new ResponseDto<>(
                                HttpStatus.OK.value(),
                                "댓글 삭제 성공",
                                true,
                                null
                        ));
    }
}
