package com.suhsaechan.dongbanza.game.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class GameRankingPageDto {
  private List<GameRankingDto> rankings;
  private int currentPage;
  private int totalPages;
  private long totalItems;

  public GameRankingPageDto(Page<GameRankingDto> rankingPage) {
    this.rankings = rankingPage.getContent();
    this.currentPage = rankingPage.getNumber();
    this.totalPages = rankingPage.getTotalPages();
    this.totalItems = rankingPage.getTotalElements();
  }
}