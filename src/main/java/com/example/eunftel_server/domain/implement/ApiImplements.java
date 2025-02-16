package com.example.eunftel_server.domain.implement;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.common.SuccessResponse;
import com.example.eunftel_server.domain.controller.ApiController;
import com.example.eunftel_server.domain.dto.MemberNotice;
import com.example.eunftel_server.domain.dto.response.GetChzzkLiveOn;
import com.example.eunftel_server.service.CafeApiService;
import com.example.eunftel_server.service.ChzzkApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/chzzkLive" )
@RequiredArgsConstructor
public class ApiImplements implements ApiController {
    private final ChzzkApiService chzzkApiService;
    private final CafeApiService cafeApiService;

    @Override
    public ResponseEntity<SuccessResponse<GetChzzkLiveOn>> getLiveStatus() {
        return SuccessResponse.of( chzzkApiService.getLiveStreams( 20 ) ).asHttp(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<MemberNotice>>> getMemberNotice( String member ) {
        //cafeApiService.getMemberNotice( "시라유키 히나" );
        return null;
    }
}
