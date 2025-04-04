package com.aesopwow.echoesofaesop.board.notice.controller;

import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeRequestDto;
import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeResponseDto;
import com.aesopwow.echoesofaesop.board.notice.service.NoticeService;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지 게시판 API", description = "공지 게시판 API")
@RestController
@Slf4j
@RequestMapping("/api/v1/notice")
public class NoticeControllerImpl implements NoticeController {
    private final NoticeService noticeService;

    public NoticeControllerImpl(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody NoticeRequestDto requestDto) {
        log.info("User Details: {}", userDetails.getUsername());
        log.info("Received request: {}", requestDto); // 요청 데이터 로깅
        NoticeResponseDto responseDto = noticeService.createNotice(userDetails, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "공지 게시글이 성공적으로 생성되었습니다.",
                                true,
                                responseDto
                        ));
    }

    @Override
    @PutMapping("/update/{noticeId}")
    public ResponseEntity<ResponseDto<NoticeResponseDto>> updateNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) {
        NoticeResponseDto responseDto = noticeService.updateNotice(userDetails, noticeId, requestDto);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "공지 게시글이 성공적으로 수정되었습니다.",
                        true,
                        responseDto
                ));
    }

    @Override
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<ResponseDto<Void>> deleteNotice(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noticeId) {
        noticeService.deleteNotice(userDetails, noticeId);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "공지 게시글이 성공적으로 삭제되었습니다.",
                        true,
                        null
                ));
    }

    @Override
    @GetMapping("/get/{noticeId}")
    public ResponseEntity<ResponseDto<NoticeResponseDto>> getNotice(
            @PathVariable Long noticeId) {
        NoticeResponseDto responseDto = noticeService.getNoticeById(noticeId);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "공지 게시글 조회 성공",
                        true,
                        responseDto
                ));
    }

    @Override
    @GetMapping("/posts")
    public ResponseEntity<ResponseDto<Page<NoticeResponseDto>>> getNoticePosts(
            @ParameterObject Pageable pageable) {
        Page<NoticeResponseDto> responseDto = noticeService.getNoticePosts(pageable);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "공지 게시판 조회 성공",
                        true,
                        responseDto
                ));
    }
}