package com.aesopwow.echoesofaesop.data.entity.user;

import com.aesopwow.echoesofaesop.common.enums.RoleType;
import com.aesopwow.echoesofaesop.data.entity.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@Table(name = "role_types")
@AttributeOverride(name = "id", column = @Column(name = "role_type_id", columnDefinition = "TINYINT"))
public class RoleTypeEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;
}