package com.aesopwow.echoesofaesop.userInfo.repository;

import com.aesopwow.echoesofaesop.data.entity.comment.FreeComment;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<FreeComment, Long> {

    @Query("SELECT COUNT(fc) FROM FreeComment fc WHERE fc.user = :user")
    int countByUser(User user);
}
