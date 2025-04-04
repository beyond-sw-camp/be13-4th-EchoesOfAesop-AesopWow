package com.aesopwow.echoesofaesop.comment.matchingcomment.controller;


import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentRequestDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentResponseDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentUpdateDto;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matching/{matchingId}/comments")
@Tag(name = "멘토링 후기 API", description = "멘토링 후기 관련 API 목록")
public interface MatchingCommentController {

    @Operation(summary = "멘토링 후기 작성")
    @PostMapping
    ResponseEntity<ResponseDto<Void>> createComment(
            @PathVariable Long matchingId,
            @RequestBody @Valid MatchingCommentRequestDto requestDto
    );

    @PutMapping("/{commentId}")
    @Operation(summary = "멘토링 후기 수정")
    ResponseEntity<ResponseDto<Void>> updateComment(
            @PathVariable Long matchingId,
            @PathVariable Long commentId,
            @RequestBody @Valid MatchingCommentUpdateDto requestDto
    );

    @GetMapping
    @Operation(summary = "멘토링 후기 전체 조회")
    ResponseEntity<ResponseDto<List<MatchingCommentResponseDto>>> getCommentsByMatchingId(
            @PathVariable Long matchingId
    );

    @DeleteMapping("/{commentId}")
    @Operation(summary = "멘토링 후기 삭제")
    ResponseEntity<ResponseDto<Void>> deleteComment(
            @PathVariable Long matchingId,
            @PathVariable Long commentId
    );
}
