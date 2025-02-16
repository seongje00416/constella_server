package com.example.eunftel_server.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Setter
@Getter
@FieldNameConstants
public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String nameE;
    private String chzzkId;
    private int season;
    private String youtubeChannel;
    private boolean isLiveOn;
}
