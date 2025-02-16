package com.example.eunftel_server.domain.controller;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.common.SuccessResponse;
import com.example.eunftel_server.domain.dto.MemberNotice;
import com.example.eunftel_server.domain.dto.response.GetChzzkLiveOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag( name = "Other API Controller", description = "외부 API 사용을 위한 컨트롤러" )
public interface ApiController {
    @Operation( summary = "치지직 방송 시작 여부 조회 API" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/isLiveOn" )
    ResponseEntity<SuccessResponse<GetChzzkLiveOn>> getLiveStatus();

    @Operation
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/isMemberNotices" )
    ResponseEntity<SuccessResponse<PageResponse<MemberNotice>>> getMemberNotice(
            @Schema( description = "member" ) String member
    );
}
