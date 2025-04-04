package com.aesopwow.echoesofaesop.data.entity.board;


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
@NoArgsConstructor
@SuperBuilder
@Table(name = "notice")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "notice_id")),
        @AttributeOverride(name = "title", column = @Column(name = "notice_title")),
        @AttributeOverride(name = "content", column = @Column(name = "notice_content")),
        @AttributeOverride(name = "views", column = @Column(name = "notice_views"))
})

public class Notice extends BaseBoardEntity {
}

