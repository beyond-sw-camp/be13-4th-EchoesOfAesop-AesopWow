package com.aesopwow.echoesofaesop.comment.freecomment.controller;

import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/free")
@Tag(name = "자유 게시판 댓글 API", description = "자유 게시판 댓글 관련 API 목록")
public interface FreeCommentController {

    @Operation(summary = "댓글 생성", description = "특정 게시글에 댓글을 생성합니다.")
    @PostMapping("/{freeId}/comment")
    ResponseEntity<ResponseDto<FreeCommentGenerationResponseDto>> createComment(
            @PathVariable Long freeId,
            @RequestBody FreeCommentGenerationDto requestDto
    );

    @Operation(summary = "댓글 수정", description = "특정 게시글의 특정 댓글을 수정합니다.")
    @PutMapping("/{freeId}/comment/{commentId}")
    ResponseEntity<ResponseDto<FreeCommentUpdateResponseDto>> updateComment(
            @PathVariable Long freeId,
            @PathVariable Long commentId,
            @RequestBody FreeCommentUpdateDto requestDto
    );

    @Operation(summary = "댓글 삭제", description = "특정 게시글의 특정 댓글을 삭제합니다.")
    @DeleteMapping("/{freeId}/comment/{commentId}")
    ResponseEntity<ResponseDto<Void>> deleteComment(
            @Parameter(description = "댓글 ID", required = true) @PathVariable Long commentId
    );
}