package com.example.eunftel_server.domain.implement;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.common.SuccessResponse;
import com.example.eunftel_server.domain.controller.SongController;
import com.example.eunftel_server.domain.dto.request.GetMusicRequest;
import com.example.eunftel_server.domain.dto.response.GetAllMusicResponse;
import com.example.eunftel_server.domain.dto.response.GetMusicDetailResponse;
import com.example.eunftel_server.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/song" )
@RequiredArgsConstructor
public class SongImplements implements SongController {

    private final SongService songService;

    @Override
    public ResponseEntity<SuccessResponse<GetMusicDetailResponse>> getMusicDetail(Long musicId) {
        return SuccessResponse.of( songService.getMusicDetail( musicId ) ).asHttp(HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getAllMusic( int size, int page ) {
        PageRequest pageRequest = PageRequest.of( page, size );
        return SuccessResponse.of( songService.getAllMusic( pageRequest ) ).asHttp( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getMusic(
            int size,
            int page,
            String title,
            String singer,
            String album
    ) {
        PageRequest pageRequest = PageRequest.of( page, size );

        return SuccessResponse.of( songService.getMusic( pageRequest, GetMusicRequest.from( title, singer, album ) ) ).asHttp( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getMusicList(int size, int page, int[] ids) {
        PageRequest pageRequest = PageRequest.of( page, size );
        return SuccessResponse.of( songService.getMusicList( pageRequest, ids ) ).asHttp( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getAlbumMusics(int size, int page, String album) {
        PageRequest pageRequest = PageRequest.of( page, size );
        return SuccessResponse.of( songService.getAlbumMusic( pageRequest, album ) ).asHttp( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<PageResponse<GetAllMusicResponse>>> getRelatedMusics(int size, int page, String object, String value) {
        PageRequest pageRequest = PageRequest.of( page, size );
        return SuccessResponse.of( songService.getRecommendMusics( pageRequest, object, value ) ).asHttp( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> getSingWith(String youtubeId) {
        return SuccessResponse.of( songService.getSingWith( youtubeId ) ).asHttp( HttpStatus.OK );
    }
}
