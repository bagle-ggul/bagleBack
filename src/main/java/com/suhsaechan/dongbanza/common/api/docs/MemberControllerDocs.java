package com.suhsaechan.dongbanza.common.api.docs;

import com.suhsaechan.dongbanza.common.jwt.dto.CustomUserDetails;
import com.suhsaechan.dongbanza.member.dto.request.MemberLoginForm;
import com.suhsaechan.dongbanza.member.dto.request.MemberSignUpForm;
import com.suhsaechan.dongbanza.member.dto.response.MemberDto;
import com.suhsaechan.dongbanza.member.dto.response.MemberLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Tag(name = "회원 API", description = "회원 관련 API")
public interface MemberControllerDocs {

  @Operation(summary = "로그인", description = "회원 로그인을 처리한다.",
      responses = {
          @ApiResponse(responseCode = "200", description = "로그인 성공",
              content = @Content(schema = @Schema(implementation = MemberLoginResponse.class))),
          @ApiResponse(responseCode = "404", description = "이메일을 찾을 수 없음"),
          @ApiResponse(responseCode = "401", description = "잘못된 비밀번호")
      })
  ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginForm form);

  @Operation(summary = "회원 가입", description = "새로운 회원 정보를 등록한다.",
      responses = {
          @ApiResponse(responseCode = "200", description = "회원 등록 성공",
              content = @Content(schema = @Schema(implementation = MemberDto.class))),
          @ApiResponse(responseCode = "409", description = "이메일 중복으로 인한 회원 등록 실패"),
          @ApiResponse(responseCode = "409", description = "닉네임 중복으로 인한 회원 등록 실패")
      })
  ResponseEntity<MemberDto> signup(@RequestBody MemberSignUpForm form);

  @Operation(summary = "내 정보 조회", description = "로그인된 사용자의 정보를 조회한다.",
      responses = {
          @ApiResponse(responseCode = "200", description = "조회 성공",
              content = @Content(schema = @Schema(implementation = MemberDto.class))),
          @ApiResponse(responseCode = "403", description = "접근 거부"),
          @ApiResponse(responseCode = "404", description = "사용자 정보를 찾을 수 없음")
      }
  )
  ResponseEntity<MemberDto> getMemberById(
      @AuthenticationPrincipal CustomUserDetails userDetails);
}
