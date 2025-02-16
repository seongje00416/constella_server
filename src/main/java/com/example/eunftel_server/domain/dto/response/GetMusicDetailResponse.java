package com.example.eunftel_server.domain.dto.response;

import com.example.eunftel_server.domain.entity.Song;
import com.example.eunftel_server.domain.type.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name = "Get Music Detail Response", description = "특정 음악의 상세 정보를 가져오는 응답" )
public record GetMusicDetailResponse(
        @Schema( description = "음악 ID" ) Long id,
        @Schema( description = "유튜브 ID" ) String youtubeID,
        @Schema( description = "부른 사람" ) Member singer,
        @Schema( description = "포함 앨범" ) String album,
        @Schema( description = "제목" ) String title,
        @Schema( description = "업로드 및 발매일" ) String uploaded,
        @Schema( description = "음악 설명" ) String description
        ) {
    public static GetMusicDetailResponse of ( Song song ){
        return GetMusicDetailResponse.builder()
                .id( song.getId() )
                .youtubeID( song.getYoutubeID() )
                .singer( song.getSinger() )
                .album( song.getAlbum() )
                .title( song.getTitle() )
                .uploaded( song.getUploaded() )
                .description( song.getDescription() )
                .build();
    }
}
