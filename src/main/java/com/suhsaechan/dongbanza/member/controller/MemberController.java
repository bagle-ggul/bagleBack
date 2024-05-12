package com.suhsaechan.dongbanza.member.controller;

import com.suhsaechan.dongbanza.common.api.docs.MemberControllerDocs;
import com.suhsaechan.dongbanza.member.dto.request.MemberLoginForm;
import com.suhsaechan.dongbanza.member.dto.request.MemberSignUpForm;
import com.suhsaechan.dongbanza.member.dto.response.MemberDto;
import com.suhsaechan.dongbanza.member.dto.response.MemberLoginResponse;
import com.suhsaechan.dongbanza.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {
  private final MemberService memberService;

  @PostMapping("login")
  public ResponseEntity<MemberLoginResponse> login(
      @RequestBody MemberLoginForm form
  ){
    MemberLoginResponse memberLoginResponse = memberService.login(form);
    return ResponseEntity.ok(memberLoginResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<MemberDto> signup(
      @RequestBody MemberSignUpForm form
  ) {
    MemberDto savedMember = memberService.save(form);
    return ResponseEntity.ok(savedMember);
  }

}
