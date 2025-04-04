package com.aesopwow.echoesofaesop.matching.service;

import com.aesopwow.echoesofaesop.data.entity.matching.Matching;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.matching.data.MatchingRepository;
import com.aesopwow.echoesofaesop.matching.data.dto.MatchingRequestDto;
import com.aesopwow.echoesofaesop.matching.data.dto.MatchingResponseDto;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {
    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    @Override
    public MatchingResponseDto createMatching(MatchingRequestDto requestDto) throws Exception {
        try {
            // FIXME: userId로 User를 가져오는 로직이 완성되면 추가
            // User user = userRepository.findById(userId)
            //         .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
            User user = userRepository.findById(1L)
                    .orElseGet(() -> {
                        User newUser = new User();
                        return userRepository.save(newUser);
                    });

            Matching matching = Matching.builder()
                    .boardType(requestDto.getBoardType())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .tag(requestDto.getTag())
                    .author(user)
                    .build();

            Matching savedMatching = matchingRepository.save(matching);

            return MatchingResponseDto.builder()
                    .matchingId(savedMatching.getId())
                    .boardType(savedMatching.getBoardType())
                    .matchingTitle(savedMatching.getTitle())
                    .matchingContent(savedMatching.getContent())
                    .matchingViews(savedMatching.getViews())
                    .authorId(savedMatching.getAuthor() != null ? savedMatching.getAuthor().getId() : null)
                    .tag(savedMatching.getTag())
                    .createdAt(savedMatching.getCreatedAt())
                    .updatedAt(savedMatching.getUpdatedAt())
                    .build();
        } catch (DataAccessException e) {
            throw new Exception("게시글 생성 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 생성 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public MatchingResponseDto updateMatching(Long matchingId, MatchingRequestDto requestDto) throws Exception {
        try {
            Matching post = matchingRepository.findById(matchingId)
                    .orElseThrow(() -> new RuntimeException("해당 매칭 게시글이 존재하지 않습니다."));

            post.setTitle(requestDto.getTitle());
            post.setContent(requestDto.getContent());
            post.setTag(requestDto.getTag());

            Matching updatedMatching = matchingRepository.saveAndFlush(post);

            return MatchingResponseDto.builder()
                    .createdAt(updatedMatching.getCreatedAt())
                    .updatedAt(updatedMatching.getUpdatedAt())
                    .boardType(updatedMatching.getBoardType())
                    .matchingId(updatedMatching.getId())
                    .matchingTitle(updatedMatching.getTitle())
                    .matchingContent(updatedMatching.getContent())
                    .matchingViews(updatedMatching.getViews())
                    .authorId(updatedMatching.getAuthor() != null ? updatedMatching.getAuthor().getId() : null)
                    .build();
        } catch (DataAccessException e) {
            throw new Exception("게시글 수정 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 수정 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void deleteMatching(Long matchingId) throws Exception {
        try {
            // FIXME: 현재 로그인한 유저의 아이디 가져오는 로직
            // Long currentUserId = getCurrentUserId();
            Long currentUserId = 1L;

            Matching matching = matchingRepository.findById(matchingId)
                    .orElseThrow(() -> new RuntimeException("해당 매칭 게시글이 존재하지 않습니다. (ID: " + matchingId + ")"));

            if (matching.getAuthor() == null) {
                throw new RuntimeException("작성자가 존재하지 않습니다.");
            }

            Long authorId = matching.getAuthor().getId();

            if (!authorId.equals(currentUserId)) {
                throw new RuntimeException("작성자만 삭제할 수 있습니다.");
            }

            matchingRepository.deleteById(matchingId);
        } catch (DataAccessException e) {
            throw new Exception("게시글 삭제 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 삭제 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Page<MatchingResponseDto> getMatchingPosts(Pageable pageable) throws Exception {
        try {
            // Matching 엔티티를 페이징 처리하여 가져오기
            Page<Matching> matchingPage = matchingRepository.findAll(pageable);

            // 각 Matching 엔티티를 MatchingResponseDto로 변환
            return matchingPage.map(matching -> MatchingResponseDto.builder()
                    .matchingId(matching.getId())
                    .createdAt(matching.getCreatedAt())
                    .updatedAt(matching.getUpdatedAt())
                    .boardType(matching.getBoardType())
                    .matchingContent(matching.getContent())
                    .matchingTitle(matching.getTitle())
                    .matchingViews(matching.getViews())
                    .tag(matching.getTag())
                    .authorId(matching.getAuthor() != null ? matching.getAuthor().getId() : null)
                    .build());

        } catch (DataAccessException e) {
            throw new Exception("게시글 목록 조회 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 목록 조회 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public MatchingResponseDto getMatchingById(Long matchingId) throws Exception {
        try {
            Matching matching = matchingRepository.findById(matchingId)
                    .orElseThrow(() -> new RuntimeException("해당 매칭 게시글이 존재하지 않습니다."));

            if (matching.getAuthor() == null) {
                throw new RuntimeException("작성자가 존재하지 않습니다.");
            }

            return MatchingResponseDto.builder()
                    .matchingId(matching.getId())
                    .createdAt(matching.getCreatedAt())
                    .updatedAt(matching.getUpdatedAt())
                    .boardType(matching.getBoardType())
                    .matchingContent(matching.getContent())
                    .matchingTitle(matching.getTitle())
                    .matchingViews(matching.getViews())
                    .authorId(matching.getAuthor().getId())
                    .build();
        } catch (RuntimeException e) {
            throw new Exception("게시글 조회 중 데이터베이스 오류가 발생했습니다. (ID: " + matchingId + ")", e);
        }
    }
}
