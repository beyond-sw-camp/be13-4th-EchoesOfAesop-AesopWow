package com.aesopwow.echoesofaesop.userInfo.repository;

import com.aesopwow.echoesofaesop.data.entity.matching.MatchingComment;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingCommentRepository extends JpaRepository<MatchingComment, Long> {

    @Query("SELECT COUNT(mc) FROM MatchingComment mc WHERE mc.userId = :user")
    int countByUserComments(User user);
}
