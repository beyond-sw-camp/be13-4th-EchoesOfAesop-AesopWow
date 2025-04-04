package com.aesopwow.echoesofaesop.applicationqueue.controller;

import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueRequestDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueResponseDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueUpdateDto;
import com.aesopwow.echoesofaesop.applicationqueue.service.ApplicationQueueService;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationQueueControllerImpl implements ApplicationQueueController {

    private final ApplicationQueueService applicationQueueService;

    @Override
    public ResponseEntity<ResponseDto<ApplicationQueueResponseDto>> createApplication(Long matchingId, ApplicationQueueRequestDto requestDto) {
        ApplicationQueueResponseDto createdApplication = applicationQueueService.createApplication(matchingId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ResponseDto<>(
                                HttpStatus.CREATED.value(),
                                "신청이 성공적으로 등록되었습니다.",
                                true,
                                createdApplication
                        )
                );
    }

    @Override
    public ResponseEntity<ResponseDto<List<ApplicationQueueResponseDto>>> getApplicationsByMatchingId(Long matchingId) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "매칭 ID에 해당하는 신청 목록을 조회하였습니다.",
                        true,
                        applicationQueueService.getApplicationsByMatchingId(matchingId)
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ApplicationQueueResponseDto>>> getApplicationsByUserId(Long userId) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "유저 ID에 해당하는 신청 목록을 조회하였습니다.",
                        true,
                        applicationQueueService.getApplicationsByUserId(userId)
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<ApplicationQueueResponseDto>> updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "신청이 성공적으로 수정되었습니다.",
                        true,
                        applicationQueueService.updateApplication(applicationQueueId, requestDto)
                ));
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> deleteApplication(Long applicationQueueId) {
        applicationQueueService.deleteApplication(applicationQueueId);

        return ResponseEntity.ok(
                        new ResponseDto<>(
                                HttpStatus.OK.value(),
                                "신청이 성공적으로 삭제되었습니다.",
                                true,
                                null
                        ));
    }
}