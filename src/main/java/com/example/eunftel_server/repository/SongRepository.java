package com.example.eunftel_server.repository;

import com.example.eunftel_server.domain.entity.Song;
import com.example.eunftel_server.domain.type.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long > {
    Page<Song> findAll( Pageable pageable );
    Page<Song> findAllByAlbum( Pageable pageable, String album );
    Page<Song> findAllBySinger( Pageable pageable, Member singer );
}
