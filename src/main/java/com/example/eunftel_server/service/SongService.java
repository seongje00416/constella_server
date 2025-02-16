package com.example.eunftel_server.service;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.common.PageUtil;
import com.example.eunftel_server.domain.dto.request.GetMusicRequest;
import com.example.eunftel_server.domain.dto.response.GetAllMusicResponse;
import com.example.eunftel_server.domain.dto.response.GetMusicDetailResponse;
import com.example.eunftel_server.domain.entity.Song;
import com.example.eunftel_server.domain.type.Member;
import com.example.eunftel_server.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public GetMusicDetailResponse getMusicDetail( Long id ){
        Song song = songRepository.findById( id ).orElseThrow();
        return GetMusicDetailResponse.of( song );
    }

    public PageResponse<GetAllMusicResponse> getAllMusic(PageRequest pageRequest){
        return PageUtil.toPageResponse( songRepository.findAll( pageRequest ) ).map(
                song -> { return GetAllMusicResponse.of( song ); }
        );
    }

    public PageResponse<GetAllMusicResponse> getMusic(PageRequest pageRequest, GetMusicRequest getMusicRequest ){
        if( getMusicRequest.member() != null ){
            Member member = Member.valueOf( getMusicRequest.member() );
            return PageUtil.toPageResponse( songRepository.findAllBySinger( pageRequest, member ) ).map(
                    song -> { return GetAllMusicResponse.of( song ); }
            );
        }
        else if( getMusicRequest.album() != null ){
            return PageUtil.toPageResponse( songRepository.findAllByAlbum( pageRequest, getMusicRequest.album() ) ).map(
                    song -> { return GetAllMusicResponse.of( song ); }
            );
        }
        else {
            return getAllMusic( pageRequest );
        }
    }

    public PageResponse<GetAllMusicResponse> getMusicList( PageRequest pageRequest, int[] musicIdList ){
        List<Song> musicList = new ArrayList<Song>();
        for( int musicId : musicIdList ){musicList.add( songRepository.findById( (long) musicId ).orElseThrow() );}
        Page<Song> songPage = new PageImpl<>(
                musicList,
                pageRequest,
                musicList.size()
        );
        return PageUtil.toPageResponse( songPage ).map( song -> { return GetAllMusicResponse.of( song ); } );
    }

    public PageResponse<GetAllMusicResponse> getAlbumMusic( PageRequest pageRequest, String album ){
        return PageUtil.toPageResponse( songRepository.findAllByAlbum( pageRequest, album ) ).map(
                song -> {return GetAllMusicResponse.of( song );}
        );
    }

    public PageResponse<GetAllMusicResponse> getRecommendMusics( PageRequest pageRequest, String object, String value ){
        Page<Song> aboutMusics;
        if( object.equals( "singer" ) ){
            Member member = Member.valueOf(value);
            aboutMusics = songRepository.findAllBySinger( pageRequest, member );
        }
        else if( object.equals( "album" ) ){
            aboutMusics = songRepository.findAllByAlbum( pageRequest, value );
        }
        else return null;

        List<Song> content = aboutMusics.getContent();
        if (content.size() > 5) {
            List<Song> shuffledContent = new ArrayList<>(content);
            Collections.shuffle(shuffledContent);
            content = shuffledContent.subList(0, 5);
        }
        Page<Song> limitedPage = new PageImpl<>(
                content,
                pageRequest,
                Math.min(aboutMusics.getTotalElements(), 5)
        );
        return PageUtil.toPageResponse( limitedPage ).map( song -> { return GetAllMusicResponse.of( song ); } );
    }

}
