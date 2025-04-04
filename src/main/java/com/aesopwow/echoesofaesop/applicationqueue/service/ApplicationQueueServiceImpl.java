package com.aesopwow.echoesofaesop.applicationqueue.service;

import com.aesopwow.echoesofaesop.applicationqueue.data.ApplicationQueueRepository;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueRequestDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueResponseDto;
import com.aesopwow.echoesofaesop.applicationqueue.data.dto.ApplicationQueueUpdateDto;
import com.aesopwow.echoesofaesop.common.enums.ApplicationQueueStatus;
import com.aesopwow.echoesofaesop.data.entity.matching.ApplicationQueue;
import com.aesopwow.echoesofaesop.data.entity.matching.Matching;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.matching.data.MatchingRepository;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplicationQueueServiceImpl implements ApplicationQueueService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;
    private final ApplicationQueueRepository applicationQueueRepository;

    @Override
    @Transactional
    public ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueRequestDto requestDto) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 매칭이 존재하지 않습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        ApplicationQueue applicationQueue = new ApplicationQueue(
                matching,
                user,
                requestDto.getContent(),
                ApplicationQueueStatus.WAIT
        );

        ApplicationQueue savedApplication = applicationQueueRepository.save(applicationQueue);
        return ApplicationQueueResponseDto.fromEntity(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId) {
        return applicationQueueRepository.findByMatchingId(matchingId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId) {
        return applicationQueueRepository.findByUserId(userId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto) {
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        applicationQueue.setContent(requestDto.getContent());
        applicationQueue.setApplicationQueueStatus(requestDto.getApplicationQueueStatus());

        ApplicationQueue updatedApplication = applicationQueueRepository.save(applicationQueue);
        return ApplicationQueueResponseDto.fromEntity(updatedApplication);
    }

    @Override
    @Transactional
    public void deleteApplication(Long applicationQueueId) {
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        applicationQueueRepository.delete(applicationQueue);
    }
}
