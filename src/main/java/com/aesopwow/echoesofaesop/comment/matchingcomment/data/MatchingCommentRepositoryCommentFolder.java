package com.aesopwow.echoesofaesop.comment.matchingcomment.data;

import com.aesopwow.echoesofaesop.data.entity.matching.MatchingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingCommentRepositoryCommentFolder extends JpaRepository<MatchingComment, Long> {
    List<MatchingComment> findByMatchingId_Id(Long matchingId);
}

