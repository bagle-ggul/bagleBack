package com.suhsaechan.dongbanza.game.dto.response;

import com.suhsaechan.dongbanza.game.domain.entity.GameResult;
import com.suhsaechan.dongbanza.member.domain.entity.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameRankingDto {
  private String characterName;
  private Integer finalScore;
  private String mbti;
  private LocalDateTime gameDate;
  private Integer gamePlaySeconds;
  private String memberEmail;
  private String details;

  public static GameRankingDto from(Member member, GameResult gameResult) {
    return GameRankingDto.builder()
        .characterName(member.getCharacterName())
        .finalScore(gameResult.getFinalScore())
        .mbti(member.getMbti())
        .gameDate(gameResult.getGameDate())
        .gamePlaySeconds(gameResult.getGamePlaySeconds())
        .details(gameResult.getDetails())
        .memberEmail(member.getEmail())
        .build();
  }
}
