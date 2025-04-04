package com.aesopwow.echoesofaesop.auth.repository;

import com.aesopwow.echoesofaesop.common.enums.RoleType;
import com.aesopwow.echoesofaesop.data.entity.user.RoleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTypeRepository extends JpaRepository<RoleTypeEntity, Long> {
    Optional<RoleTypeEntity> findByRoleType(RoleType roleType);
}
