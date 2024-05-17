package com.suhsaechan.dongbanza.game.repository;

import com.suhsaechan.dongbanza.game.domain.entity.GameResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {
  List<GameResult> findByMemberId(Long memberId);
  Optional<GameResult> findByIdAndMemberId(Long id, Long memberId);
  Page<GameResult> findAllByOrderByFinalScoreDesc(Pageable pageable);
}
