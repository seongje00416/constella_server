package com.example.eunftel_server.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Schema( name = "맴버들의 치지직 고유 코드" )
public record GetChzzkLiveOn(
        @Schema( name = "유니" ) boolean yuniLive,
        @Schema( name = "히나" ) boolean hinaLive,
        @Schema( name = "마시로" ) boolean mashiroLive,
        @Schema( name = "리제" ) boolean lizeLive,
        @Schema( name = "타비" ) boolean tabiLive,
        @Schema( name = "시부키" ) boolean shibukiLive,
        @Schema( name = "린" ) boolean rinLive,
        @Schema( name = "나나" ) boolean nanaLive,
        @Schema( name = "리코" ) boolean rikoLive
) {
    public static GetChzzkLiveOn of(
            boolean yuni,
            boolean hina,
            boolean mashiro,
            boolean lize,
            boolean tabi,
            boolean shibuki,
            boolean rin,
            boolean nana,
            boolean riko
    ) {
        return GetChzzkLiveOn.builder()
                .yuniLive( yuni )
                .hinaLive( hina )
                .mashiroLive( mashiro )
                .lizeLive( lize )
                .tabiLive( tabi )
                .shibukiLive( shibuki )
                .rinLive( rin )
                .nanaLive( nana )
                .rikoLive( riko )
                .build();

    }
}
