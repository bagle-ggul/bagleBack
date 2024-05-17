package com.suhsaechan.dongbanza.game.dto.response;

import com.suhsaechan.dongbanza.game.domain.entity.GameResult;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GameResultResponse {

  private Long id;
  private Integer finalScore;
  private Boolean success;
  private LocalDateTime gameDate;
  private LocalDateTime gamePlayTime;
  private String details;

  public static GameResultResponse from(GameResult gameResult) {
    return GameResultResponse.builder()
        .id(gameResult.getId())
        .finalScore(gameResult.getFinalScore())
        .success(gameResult.getSuccess())
        .gameDate(gameResult.getGameDate())
        .gamePlayTime(gameResult.getGamePlayTime())
        .details(gameResult.getDetails())
        .build();
  }
}
