package com.example.eunftel_server.domain.dto.response;

import com.example.eunftel_server.domain.entity.Song;
import com.example.eunftel_server.domain.type.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name = "Get Music Response", description = "음악 목록 조회시 필요한 정보" )
public record GetAllMusicResponse(
        @Schema( description = "음악 ID" ) Long id,
        @Schema( description = "유튜브 ID" ) String youtubeID,
        @Schema( description = "제목" ) String title,
        @Schema( description = "가수" ) Member singer
) {
    public static GetAllMusicResponse of(Song song ){
        return GetAllMusicResponse.builder()
                .id( song.getId() )
                .youtubeID( song.getYoutubeID() )
                .title( song.getTitle() )
                .singer( song.getSinger() )
                .build();
    }
}
