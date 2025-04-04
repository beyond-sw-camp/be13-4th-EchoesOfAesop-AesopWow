package com.aesopwow.echoesofaesop.applicationqueue.data;

import com.aesopwow.echoesofaesop.data.entity.matching.ApplicationQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationQueueRepository extends JpaRepository<ApplicationQueue, Long> {

    // 새로운 메서드 추가 (매칭 ID 기준 조회)
    List<ApplicationQueue> findByMatchingId(Long matchingId);

    // 새로운 메서드 추가 (유저 ID 기준 조회)
    List<ApplicationQueue> findByUserId(Long userId);
}
