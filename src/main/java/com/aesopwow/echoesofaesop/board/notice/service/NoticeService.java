package com.aesopwow.echoesofaesop.board.notice.service;

import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeRequestDto;
import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface NoticeService {
    NoticeResponseDto createNotice(UserDetails userDetails, NoticeRequestDto requestDto);

    NoticeResponseDto updateNotice(UserDetails userDetails, Long noticeId, NoticeRequestDto requestDto);

    void deleteNotice(UserDetails userDetails, Long id);

    Page<NoticeResponseDto> getNoticePosts(Pageable pageable);

    NoticeResponseDto getNoticeById(Long noticeId);
}
