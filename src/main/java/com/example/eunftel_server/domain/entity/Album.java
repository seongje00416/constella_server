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
public class Album {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    @Enumerated( EnumType.STRING )
    private Member singer;
    private String coverImage;
    private String description;
    private String publishDate;
}
