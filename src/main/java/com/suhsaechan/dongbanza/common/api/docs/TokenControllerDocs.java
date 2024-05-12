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

  @Operation(summary = "새로운 AccessToken 재발급",
      description = "Refresh Token 을 받아서 새로운 AccessToken 을 재발급해준다.",
      responses = {
          @ApiResponse(responseCode = "201", description = "AccessToken 정상 재발급",
              content = @Content(schema = @Schema(implementation = CreateAccessTokenResponse.class)))
      })
  ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request);
}