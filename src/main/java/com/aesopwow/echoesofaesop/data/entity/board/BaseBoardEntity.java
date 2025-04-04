package com.aesopwow.echoesofaesop.data.entity.board;

import com.aesopwow.echoesofaesop.common.enums.BoardType;
import com.aesopwow.echoesofaesop.data.entity.common.BaseEntity;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseBoardEntity extends BaseEntity {

    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private String title;

    private String content;

    @Builder.Default
    @Column(columnDefinition = "integer default 0")
    private int views = 0;

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
        }
        this.title = title;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 비어있을 수 없습니다.");
        }
        this.content = content;
    }

    public void setViews(int views) {
        if (views < 0) {
            throw new IllegalArgumentException("조회수는 음수가 될 수 없습니다.");
        }
        this.views = views;
    }
}