package com.example.eunftel_server.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name = "Get Music Request", description = "음악 특정 조건 검색을 위한 요청")
public record GetMusicRequest(
        @Schema( description = "검색어 - 제목" ) String title,
        @Schema( description = "검색어 - 가수" ) String member,
        @Schema( description = "검색어 - 앨범" ) String album
) {
    public static GetMusicRequest from( String title, String singer, String album ){
        return GetMusicRequest.builder()
                .title( title )
                .member( singer )
                .album( album )
                .build();
    }
}
