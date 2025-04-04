package com.aesopwow.echoesofaesop.data.entity.user;

import com.aesopwow.echoesofaesop.common.enums.Gender;
import com.aesopwow.echoesofaesop.common.enums.ProfileType;
import com.aesopwow.echoesofaesop.data.entity.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@Table(name = "user_profiles")
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserProfile extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "current_job")
    private String currentJob;

    @Column(name = "desired_job")
    private String desiredJob;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "experience", columnDefinition = "TEXT")
    private String experience;

    @Column(name = "rate")
    private float rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_type")
    private ProfileType profileType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

}