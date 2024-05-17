package com.suhsaechan.dongbanza.member.domain.entity;

import com.suhsaechan.dongbanza.common.entity.BaseEntity;
import com.suhsaechan.dongbanza.member.domain.constants.MemberRole;
import com.suhsaechan.dongbanza.member.domain.constants.MemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString(exclude = "password")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  private String password;

  @Column(name = "nickname", nullable = false, unique = true)
  private String nickname;

  @Enumerated(EnumType.STRING)
  private MemberRole role;

  @Enumerated(EnumType.STRING)
  private MemberStatus status;

  private String refreshToken;

  @Setter
  private String profileImageUrl;

  private Integer score; // 호감도

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "gender", nullable = false)
  private String gender;

  @Column(name = "mbti", nullable = false)
  private String mbti;

  @Column(name = "regression_count", nullable = false)
  private Integer regressionCount;

  @Column(name = "game_progress", nullable = false)
  private String gameProgress;

  public void updateRefreshToken(String refreshToken){
    this.refreshToken = refreshToken;
  }

  public void increaseRegressionCount() {
    this.regressionCount += 1;
  }

  public void updateScore(int value) {
    this.score += value;
  }

  public void updateGameProgress(String progress) {
    this.gameProgress = progress;
  }
}
