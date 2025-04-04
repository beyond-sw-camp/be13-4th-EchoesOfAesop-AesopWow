package com.aesopwow.echoesofaesop.comment.freecomment.service;

import com.aesopwow.echoesofaesop.comment.freecomment.data.FreeCommentRepository;
import com.aesopwow.echoesofaesop.data.entity.board.Free;
import com.aesopwow.echoesofaesop.data.entity.comment.FreeComment;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.aesopwow.echoesofaesop.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;
import com.aesopwow.echoesofaesop.common.enums.CommentType;
import com.aesopwow.echoesofaesop.board.free.data.FreeRepository;
import com.aesopwow.echoesofaesop.userInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FreeCommentServiceImpl implements FreeCommentService {

    private final FreeCommentRepository freeCommentRepository;
    private final UserRepository userRepository;
    private final FreeRepository freeRepository;

    @Override
    @Transactional
    public FreeCommentGenerationResponseDto createComment(Long freeId, FreeCommentGenerationDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Free free = freeRepository.findById(freeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        CommentType commentType = requestDto.getCommentType();

        FreeComment freeComment = new FreeComment();
        freeComment.setContent(requestDto.getContent());
        freeComment.setUser(user);
        freeComment.setFree(free);
        freeComment.setCommentType(commentType);

        freeCommentRepository.save(freeComment);

        return fromEntity(freeComment);
    }

    private FreeCommentGenerationResponseDto fromEntity(FreeComment freeComment) {
        return new FreeCommentGenerationResponseDto(
                freeComment.getId(),
                freeComment.getContent(),
                freeComment.getUser().getNickName(),
                freeComment.getCommentType().toString()
        );
    }

    @Override
    @Transactional
    public FreeCommentUpdateResponseDto updateComment(Long freeId, Long commentId, FreeCommentUpdateDto requestDto) {
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (freeComment.getFree().getId() != freeId) {
            throw new IllegalArgumentException("해당 게시글에 속하지 않는 댓글입니다.");
        }

        freeComment.setContent(requestDto.getContent());

        FreeComment updatedComment = freeCommentRepository.save(freeComment);
        return mapToDto(updatedComment);
    }

    private FreeCommentUpdateResponseDto mapToDto(FreeComment freeComment) {
        return new FreeCommentUpdateResponseDto(
                freeComment.getId(),
                freeComment.getContent(),
                freeComment.getUser().getId(),
                freeComment.getFree().getId()
        );
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        freeCommentRepository.delete(freeComment);
    }
}
