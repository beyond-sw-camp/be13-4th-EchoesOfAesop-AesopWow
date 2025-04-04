package com.aesopwow.echoesofaesop.comment.matchingcomment.controller;

import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentRequestDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentResponseDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentUpdateDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.service.MatchingCommentService;

import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchingCommentControllerImpl implements MatchingCommentController {

    private final MatchingCommentService matchingCommentService;

    @Override
    public ResponseEntity<ResponseDto<Void>> createComment(Long matchingId, MatchingCommentRequestDto requestDto) {
        matchingCommentService.createMatchingComment(matchingId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "멘토링 후기 생성 성공",
                                true,
                                null
                        ));
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> updateComment(Long matchingId, Long commentId, MatchingCommentUpdateDto requestDto) {
        matchingCommentService.updateMatchingComment(matchingId, commentId, requestDto);
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "멘토링 후기 수정 성공",
                        true,
                        null
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<List<MatchingCommentResponseDto>>> getCommentsByMatchingId(Long matchingId) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "멘토링 후기 전체 조회 성공",
                        true,
                        matchingCommentService.getCommentsByMatchingId(matchingId)
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> deleteComment(Long matchingId, Long commentId) {
        matchingCommentService.deleteMatchingComment(matchingId, commentId);

        return ResponseEntity.ok(
                        new ResponseDto<>(
                                HttpStatus.OK.value(),
                                "멘토링 후기 삭제 성공",
                                true,
                                null
                        ));
    }
}
