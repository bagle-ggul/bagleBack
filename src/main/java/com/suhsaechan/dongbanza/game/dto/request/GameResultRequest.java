package com.suhsaechan.dongbanza.game.dto.request;

import com.suhsaechan.dongbanza.game.domain.entity.GameResult;
import com.suhsaechan.dongbanza.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GameResultRequest {

  private Integer finalScore;
  private Boolean success;
  private String details;
  private Integer gamePlaySeconds;

  public static GameResultRequest from(GameResult gameResult) {
    return GameResultRequest.builder()
        .finalScore(gameResult.getFinalScore())
        .success(gameResult.getSuccess())
        .details(gameResult.getDetails())
        .gamePlaySeconds(gameResult.getGamePlaySeconds())
        .build();
  }

  public GameResult toGameResult(Member member) {
    return GameResult.builder()
        .member(member)
        .finalScore(this.finalScore)
        .success(this.success)
        .gameDate(LocalDateTime.now())
        .gamePlaySeconds(this.gamePlaySeconds)
        .details(this.details)
        .build();
  }
}
