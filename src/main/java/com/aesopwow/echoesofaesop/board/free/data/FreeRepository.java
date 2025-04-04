package com.aesopwow.echoesofaesop.board.free.data;

import com.aesopwow.echoesofaesop.data.entity.board.Free;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeRepository extends JpaRepository<Free, Long> {

    // 제목으로 검색
    Page<Free> findByTitleContaining(String titleKeyword, Pageable pageable);

    // 내용으로 검색
    Page<Free> findByContentContaining(String contentKeyword, Pageable pageable);

    // 제목 + 내용 검색
    Page<Free> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);
}

