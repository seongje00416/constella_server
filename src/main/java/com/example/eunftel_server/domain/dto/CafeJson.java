package com.example.eunftel_server.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeJson {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;

    @Getter
    @Data
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String cafename;
        private String cafeurl;
    }
}