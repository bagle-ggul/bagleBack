package com.suhsaechan.dongbanza.game.controller;

import com.suhsaechan.dongbanza.common.api.docs.GameResultControllerDocs;
import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import com.suhsaechan.dongbanza.game.dto.request.GameResultRequest;
import com.suhsaechan.dongbanza.game.dto.response.GameRankingDto;
import com.suhsaechan.dongbanza.game.dto.response.GameResultResponse;
import com.suhsaechan.dongbanza.game.service.GameResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameResultController implements GameResultControllerDocs {

  private final GameResultService gameResultService;

  @PostMapping("/over")
  public ResponseEntity<GameResultResponse> saveGameResult(
      @RequestBody GameResultRequest gameResultRequest,
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    GameResultResponse gameResultResponse
        = gameResultService.saveGameResult(gameResultRequest, userDetails.getMember());
    return ResponseEntity.status(HttpStatus.CREATED).body(gameResultResponse);
  }

  @GetMapping("/ranking")
  public ResponseEntity<List<GameRankingDto>> getGameRanking(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    List<GameRankingDto> gameRankingDtoList = gameResultService.getGameRankingList(
        page, size);
    return ResponseEntity.ok(gameRankingDtoList);
  }

  @GetMapping("/my-results")
  public ResponseEntity<List<GameResultResponse>> getMyGameResults(
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    List<GameResultResponse> gameResults = gameResultService.getGameResultsByMember(
        userDetails.getMember().getId());
    return ResponseEntity.ok(gameResults);
  }

  @DeleteMapping("/my-results/{gameResultId}")
  public ResponseEntity<Void> deleteGameResult(
      @PathVariable Long gameResultId,
      @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    gameResultService.deleteGameResult(gameResultId,
        userDetails.getMember().getId());
    return ResponseEntity.noContent().build();
  }
}
