package com.aesopwow.echoesofaesop.data.entity.matching;


import com.aesopwow.echoesofaesop.data.entity.comment.BaseCommentEntity;
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
@Table(name = "matching_comment")
@Setter
@Getter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_comment_id")),
})
public class MatchingComment extends BaseCommentEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id", referencedColumnName = "matching_id")
    private Matching matchingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_queue_id", referencedColumnName = "application_queue_id")
    private ApplicationQueue applicationQueueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "rate", nullable = false)
    private float rate;
}