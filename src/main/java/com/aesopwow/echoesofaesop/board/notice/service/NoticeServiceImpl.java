package com.aesopwow.echoesofaesop.board.notice.service;

import com.aesopwow.echoesofaesop.board.notice.data.NoticeRepository;
import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeRequestDto;
import com.aesopwow.echoesofaesop.board.notice.data.dto.NoticeResponseDto;
import com.aesopwow.echoesofaesop.data.entity.board.Notice;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public NoticeResponseDto createNotice(UserDetails userDetails, NoticeRequestDto requestDto) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(
                        () -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다.")
                );

        Notice notice = Notice.builder()
                .boardType(requestDto.getBoardType())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(user)
                .build();

        Notice savedNotice = noticeRepository.save(notice);

        return NoticeResponseDto.builder()
                .noticeId(savedNotice.getId())
                .boardType(savedNotice.getBoardType())
                .noticeTitle(savedNotice.getTitle())
                .noticeContent(savedNotice.getContent())
                .noticeViews(savedNotice.getViews())
                .authorId(savedNotice.getAuthor() != null ? savedNotice.getAuthor().getId() : null)
                .createDate(savedNotice.getCreatedAt())
                .updateDate(savedNotice.getUpdatedAt())
                .build();
    }

    @Override
    public NoticeResponseDto updateNotice(UserDetails userDetails, Long noticeId, NoticeRequestDto requestDto) {
        Notice post = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        if (user.getId() != post.getAuthor().getId()) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        Notice updatedNotice = noticeRepository.saveAndFlush(post);

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .noticeId(updatedNotice.getId())
                .noticeTitle(updatedNotice.getTitle())
                .noticeContent(updatedNotice.getContent())
                .boardType(updatedNotice.getBoardType())
                .updateDate(updatedNotice.getUpdatedAt())
                .build();

        return responseDto;
    }

    @Override
    public void deleteNotice(UserDetails userDetails, Long noticeId) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

        if (notice.getAuthor() == null) {
            throw new RuntimeException("작성자가 존재하지 않습니다.");
        }

        Long authorId = notice.getAuthor().getId();

        if (user.getId() != authorId) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        noticeRepository.deleteById(noticeId);
    }

    @Override
    public NoticeResponseDto getNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

        if (notice.getAuthor() == null) {
            throw new RuntimeException("작성자가 존재하지 않습니다.");
        }

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .noticeId(notice.getId())
                .createDate(notice.getCreatedAt())
                .updateDate(notice.getUpdatedAt())
                .boardType(notice.getBoardType())
                .noticeContent(notice.getContent())
                .noticeTitle(notice.getTitle())
                .noticeViews(notice.getViews())
                .authorId(notice.getAuthor().getId())
                .build();

        return responseDto;
    }

    @Override
    public Page<NoticeResponseDto> getNoticePosts(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);

        return noticePage.map(notice -> NoticeResponseDto.builder()
                .noticeId(notice.getId())
                .createDate(notice.getCreatedAt())
                .updateDate(notice.getUpdatedAt())
                .boardType(notice.getBoardType())
                .noticeContent(notice.getContent())
                .noticeTitle(notice.getTitle())
                .noticeViews(notice.getViews())
                .authorId(notice.getAuthor() != null ? notice.getAuthor().getId() : null)
                .build()
        );
    }
}