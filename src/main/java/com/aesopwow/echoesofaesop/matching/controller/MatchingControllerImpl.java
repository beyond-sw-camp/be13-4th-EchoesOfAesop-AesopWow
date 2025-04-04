package com.aesopwow.echoesofaesop.matching.controller;

import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import com.aesopwow.echoesofaesop.matching.data.dto.MatchingRequestDto;
import com.aesopwow.echoesofaesop.matching.data.dto.MatchingResponseDto;
import com.aesopwow.echoesofaesop.matching.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "매칭 게시판 API", description = "매칭 게시판 API")
@RestController
@RequestMapping("/api/v1/matching")
@RequiredArgsConstructor
public class MatchingControllerImpl implements MatchingController {
    private final MatchingService matchingService;

    @Override
    public ResponseEntity<ResponseDto<MatchingResponseDto>> createMatching(
            @RequestBody MatchingRequestDto requestDto) {
        try {
            MatchingResponseDto responseDto = matchingService.createMatching(requestDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            new ResponseDto<>(
                                    HttpStatus.CREATED.value(),
                                    "매칭 게시글 생성 성공",
                                    true,
                                    responseDto
                            ));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<MatchingResponseDto>> updateMatching(
            Long matchingId,
            MatchingRequestDto requestDto) {
        try {
            MatchingResponseDto responseDto = matchingService.updateMatching(matchingId, requestDto);

            return ResponseEntity.ok(
                    new ResponseDto<>(
                            HttpStatus.OK.value(),
                            "매칭 게시글 수정 성공",
                            true,
                            responseDto
                    ));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> deleteMatching(Long matchingId) {
        try {
            matchingService.deleteMatching(matchingId);

            return ResponseEntity.ok(
                    new ResponseDto<>(
                            HttpStatus.OK.value(),
                            "매칭 게시글 삭제 성공",
                            true,
                            null
                    ));
//        } catch (UnauthorizedException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Page<MatchingResponseDto>>> getMatchingPosts(Pageable pageable) {
        try {
            Page<MatchingResponseDto> responseDto = matchingService.getMatchingPosts(pageable);

            return ResponseEntity.ok(
                    new ResponseDto<>(
                            HttpStatus.OK.value(),
                            "매칭 게시판 조회 성공",
                            true,
                            responseDto
                    ));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<MatchingResponseDto>> getMatchingById(Long matchingId) {
        try {
            MatchingResponseDto responseDto = matchingService.getMatchingById(matchingId);

            return ResponseEntity.ok(
                    new ResponseDto<>(
                            HttpStatus.OK.value(),
                            "매칭 게시글 조회 성공",
                            true,
                            responseDto
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
