package com.example.eunftel_server.domain.entity;

import com.example.eunftel_server.domain.type.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String youtubeID;
    private String title;
    @Enumerated( EnumType.STRING )
    private Member singer;
    private String album;
    private String uploaded;
    private String description;
}
