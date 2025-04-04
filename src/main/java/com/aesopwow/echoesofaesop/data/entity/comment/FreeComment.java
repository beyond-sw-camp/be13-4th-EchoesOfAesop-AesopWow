package com.aesopwow.echoesofaesop.data.entity.comment;

import com.aesopwow.echoesofaesop.data.entity.board.Free;
import com.aesopwow.echoesofaesop.data.entity.user.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "free_comment")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_comment_id")),
        @AttributeOverride(name = "content", column = @Column(name = "free_comment_content"))
})
public class FreeComment extends BaseCommentEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_id")
    private Free free;
}