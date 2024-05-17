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

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  private String password;

  @Column(name = "nickname", nullable = false, unique = true)
  private String nickname;

  private String phone;

  @Enumerated(EnumType.STRING)
  private MemberRole role;

  @Enumerated(EnumType.STRING)
  private MemberStatus status;

  private String refreshToken;

  @Setter
  private String profileImageUrl;

  @Setter
  private String address;

  @Setter
  private Double latitude;

  @Setter
  private Double longitude;

  public void updateRefreshToken(String refreshToken){
    this.refreshToken = refreshToken;
  }
}
