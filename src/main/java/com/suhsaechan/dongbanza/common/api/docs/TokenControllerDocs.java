package com.suhsaechan.dongbanza.common.api.docs;

import com.suhsaechan.dongbanza.common.jwt.dto.request.CreateAccessTokenRequest;
import com.suhsaechan.dongbanza.common.jwt.dto.response.CreateAccessTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TokenControllerDocs {

  @Operation(summary = "새로운 AccessToken 재발급", description = "Refresh Token을 이용하여 새로운 AccessToken을 발급받는다.",
      responses = {
          @ApiResponse(responseCode = "201", description = "AccessToken 정상 재발급",
              content = @Content(schema = @Schema(implementation = CreateAccessTokenResponse.class))),
          @ApiResponse(responseCode = "400", description = "잘못된 Refresh Token"),
          @ApiResponse(responseCode = "401", description = "만료된 Refresh Token")
      })
  ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
      @RequestBody CreateAccessTokenRequest request);
}
