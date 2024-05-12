package com.suhsaechan.dongbanza.common.jwt.domain.repository;

import com.suhsaechan.dongbanza.common.jwt.domain.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByMemberId(Long memberId);
  Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
