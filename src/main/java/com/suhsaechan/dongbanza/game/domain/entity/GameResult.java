package com.suhsaechan.dongbanza.game.domain.entity;

import com.suhsaechan.dongbanza.member.domain.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GameResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member; // 사용자

  private Integer finalScore;

  private Boolean success;

  private LocalDateTime gameDate;

  private LocalDateTime gamePlayTime;

  private String details; // 게임 결과 세부 내용 (예: JSON 형식)

  public void updateDetails(String details) {
    this.details = details;
  }
}