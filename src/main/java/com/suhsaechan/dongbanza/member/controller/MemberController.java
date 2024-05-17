package com.suhsaechan.dongbanza.member.controller;

import com.suhsaechan.dongbanza.common.api.docs.MemberControllerDocs;
import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import com.suhsaechan.dongbanza.common.log.LogMonitoringInvocation;
import com.suhsaechan.dongbanza.member.dto.request.MemberLoginForm;
import com.suhsaechan.dongbanza.member.dto.request.MemberSignUpForm;
import com.suhsaechan.dongbanza.member.dto.response.MemberDto;
import com.suhsaechan.dongbanza.member.dto.response.MemberLoginResponse;
import com.suhsaechan.dongbanza.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemberController implements MemberControllerDocs {

  private final MemberService memberService;

  @LogMonitoringInvocation
  @PostMapping("login")
  public ResponseEntity<MemberLoginResponse> login(
      @RequestBody MemberLoginForm form
  ) {
    MemberLoginResponse memberLoginResponse = memberService.login(form);
    return ResponseEntity.ok()
        .header("Authorization",
            "Bearer " + memberLoginResponse.getAccessToken())
        .body(MemberLoginResponse.exceptAccessToken(memberLoginResponse));
  }
  @LogMonitoringInvocation
  @PostMapping("/signup")
  public ResponseEntity<MemberDto> signup(
      @RequestBody MemberSignUpForm form
  ) {
    MemberDto savedMember = memberService.save(form);
    return ResponseEntity.ok(savedMember);
  }

  @LogMonitoringInvocation
  @GetMapping("/my-page")
  public ResponseEntity<MemberDto> getMemberById(
      @AuthenticationPrincipal CustomUserDetails userDetails) {

    if (userDetails == null) {
      log.error("userDetails == null");
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    MemberDto memberDto
        = memberService.getMemberById(userDetails.getMember().getId());
    return ResponseEntity.ok(memberDto);
  }
}