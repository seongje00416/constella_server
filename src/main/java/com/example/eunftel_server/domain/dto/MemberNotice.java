package com.example.eunftel_server.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
public record MemberNotice(
        @Schema( description = "Title" ) String title,
        @Schema( description = "Date" ) String date,
        @Schema( description = "Writer" ) String writer,
        @Schema( description = "Content" ) String content
) {
    public static MemberNotice of( String title, String date, String writer, String content ){
        return MemberNotice.builder()
                .title( title )
                .date( date )
                .writer( writer )
                .content( content )
                .build();
    }
}
