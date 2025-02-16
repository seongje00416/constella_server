package com.example.eunftel_server.domain.controller;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.common.SuccessResponse;
import com.example.eunftel_server.domain.dto.request.GetMusicRequest;
import com.example.eunftel_server.domain.dto.response.GetAllMusicResponse;
import com.example.eunftel_server.domain.dto.response.GetMusicDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag( name = "Song API", description = "음악 관련 컨트롤러" )
public interface SongController {
    // 노래 상세 조회
    @Operation( summary = "노래 상세 조회 API", description = "노래 정보 상세 조회" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/music/{musicId}" )
    ResponseEntity<SuccessResponse<GetMusicDetailResponse>> getMusicDetail(
            @PathVariable Long musicId
    );
    
    // 노래 목록 조회
    @Operation( summary = "노래 목록 조회", description = "노래 목록 전체 조회" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/musicAll" )
    ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getAllMusic (
        @RequestParam( value = "size" ) int size,
        @RequestParam( value = "page" ) int page
    );
    
    // 노래 특정 조건 검색
    @Operation( summary = "노래 조건 검색 조회", description = "특정 조건에 만족하는 노래 조회" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/music" )
    ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getMusic (
            @RequestParam( value = "size" ) int size,
            @RequestParam( value = "page" ) int page,
            @RequestParam( value = "title" ) String title,
            @RequestParam( value = "singer" ) String singer,
            @RequestParam( value = "album" ) String album
    );

    @Operation( summary = "노래 ID를 통한 노래 목록 조회", description = "노래 ID 리스트를 받아 해당 노래의 정보를 조회" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/musicList" )
    ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getMusicList(
            @RequestParam( value = "size" ) int size,
            @RequestParam( value = "page" ) int page,
            @RequestParam( value = "musicIds" ) int[] ids
    );

    @Operation( summary = "앨범에 속한 노래 조회", description = "앨범 이름을 받아 포함하고 있는 노래 정보들을 조회" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/albumMusic" )
    ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getAlbumMusics(
            @RequestParam( value = "size" ) int size,
            @RequestParam( value = "page" ) int page,
            @RequestParam( value = "album" ) String album
    );

    @Operation( summary = "노래 추천 API", description = "관련된 음악을 랜덤으로 최대 5개 추천해주는 API" )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공"
            )
    })
    @GetMapping( "/recommendMusics" )
    ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getRelatedMusics(
            @RequestParam( value = "size" ) int size,
            @RequestParam( value = "page" ) int page,
            @RequestParam( value = "object" ) String object,
            @RequestParam( value = "value" ) String value
    );
}
