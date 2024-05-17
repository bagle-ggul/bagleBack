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
  private LocalDateTime gamePlayTime;

  public static GameResultRequest from(GameResult gameResult) {
    return GameResultRequest.builder()
        .finalScore(gameResult.getFinalScore())
        .success(gameResult.getSuccess())
        .details(gameResult.getDetails())
        .gamePlayTime(gameResult.getGamePlayTime())
        .build();
  }

  public GameResult toGameResult(Member member) {
    return GameResult.builder()
        .member(member)
        .finalScore(this.finalScore)
        .success(this.success)
        .gameDate(LocalDateTime.now())
        .gamePlayTime(this.gamePlayTime)
        .details(this.details)
        .build();
  }
}
