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

  @Column(nullable = false, unique = true)
  private String email;

  private String password;

  @Column(nullable = false, unique = true)
  private String characterName;

  @Enumerated(EnumType.STRING)
  private MemberRole role;

  @Enumerated(EnumType.STRING)
  private MemberStatus status;

  private String refreshToken;

  @Setter
  private String profileImageUrl;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String gender;

  @Column(nullable = false)
  private String mbti;

  @Column(nullable = false)
  private Integer totalRegressionCount; // 사용자의 전체 게임의 회귀횟수

  @Column(nullable = false)
  private Integer totalScore; // 총 획득한 호감도

  @Column(nullable = false)
  private String gameProgress;

  public void updateRefreshToken(String refreshToken){
    this.refreshToken = refreshToken;
  }

  public void increaseRegressionCount() {
    this.totalRegressionCount += 1;
  }

  public void updateScore(int value) {
    this.totalScore += value;
  }

  public void updateGameProgress(String progress) {
    this.gameProgress = progress;
  }
}
