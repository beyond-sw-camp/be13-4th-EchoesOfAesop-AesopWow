package com.aesopwow.echoesofaesop.data.entity.board;

import com.aesopwow.echoesofaesop.data.entity.comment.FreeComment;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "free")
@Getter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_views", nullable = false, columnDefinition = "INT DEFAULT 0"))
})
public class Free extends BaseBoardEntity {
    @OneToMany(mappedBy = "free", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FreeComment> comments = new ArrayList<>();
}

