package com.aesopwow.echoesofaesop.comment.matchingcomment.service;


import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentRequestDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentResponseDto;
import com.aesopwow.echoesofaesop.comment.matchingcomment.data.dto.MatchingCommentUpdateDto;

import java.util.List;

public interface MatchingCommentService {

    void createMatchingComment(Long matchingId, MatchingCommentRequestDto requestDto);

    void updateMatchingComment(Long matchingId, Long commentId, MatchingCommentUpdateDto requestDto);

    List<MatchingCommentResponseDto> getCommentsByMatchingId(Long matchingId);

    void deleteMatchingComment(Long matchingId, Long commentId);
}
