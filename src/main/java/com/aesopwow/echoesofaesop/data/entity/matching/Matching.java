package com.aesopwow.echoesofaesop.data.entity.matching;

import com.aesopwow.echoesofaesop.data.entity.board.BaseBoardEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "matching")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_id")),
        @AttributeOverride(name = "title", column = @Column(name = "matching_title")),
        @AttributeOverride(name = "content", column = @Column(name = "matching_content")),
        @AttributeOverride(name = "views", column = @Column(name = "matching_views"))
})
public class Matching extends BaseBoardEntity {
    @Column(nullable = false)
    private String tag; // 태그 (예: 작업, 기술)

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
        }
        super.setTitle(title); // BaseBoardEntity의 setTitle 메서드를 호출
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 비어있을 수 없습니다.");
        }
        super.setContent(content);
    }

    public void setTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("태그는 비어있을 수 없습니다.");
        }
        this.tag = tag;
    }
}