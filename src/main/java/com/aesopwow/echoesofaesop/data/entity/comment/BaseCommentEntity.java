package com.aesopwow.echoesofaesop.data.entity.comment;

import com.aesopwow.echoesofaesop.common.enums.CommentType;
import com.aesopwow.echoesofaesop.data.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseCommentEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_type", nullable = false)
    private CommentType commentType;
}

