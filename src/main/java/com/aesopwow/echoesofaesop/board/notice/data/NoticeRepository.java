package com.aesopwow.echoesofaesop.board.notice.data;

import com.aesopwow.echoesofaesop.data.entity.board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice, Long> {
}