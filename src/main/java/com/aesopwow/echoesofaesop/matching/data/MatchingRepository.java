package com.aesopwow.echoesofaesop.matching.data;

import com.aesopwow.echoesofaesop.data.entity.matching.Matching;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    @Query("SELECT COUNT(m) FROM Matching m WHERE m.author = :user")
    int countByUser(User user);
}
