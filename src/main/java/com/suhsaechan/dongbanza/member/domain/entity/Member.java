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
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ToString(exclude = "password")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Member extends BaseEntity implements UserDetails {

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

  @Setter
  private String profileImageUrl;

  @Setter
  private String address;

  @Setter
  private Double latitude;

  @Setter
  private Double longitude;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return List.of(new SimpleGrantedAuthority("member"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    //TODO : 만료되었는지 확인하는 로직
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    //TODO : 계정이 잠금되었는지 확인하는 로직
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    //TODO :패스워드 만료되었는지 확인하는 로직
    return false;
  }

  @Override
  public boolean isEnabled() {
    //TODO : 계정이 사용 가능한지 확인하는 로직
    return false;
  }
}
