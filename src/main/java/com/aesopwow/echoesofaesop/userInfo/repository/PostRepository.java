package com.aesopwow.echoesofaesop.userInfo.repository;

import com.aesopwow.echoesofaesop.data.entity.board.Free;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Free, Long> {

    @Query("SELECT COUNT(f) FROM Free f WHERE f.author = :user")
    int countByUser(User user);
}
