package com.example.eunftel_server.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChzzkJson {
    private int code;
    private String message;
    private Content content;

    @Getter
    @Data
    public static class Content {
        private Page page;
        private List<LiveStream> data;
    }
    @Getter
    @Data
    public static class Page {
        private String next;
    }
    @Getter
    @Data
    public static class LiveStream {
        private Long liveId;
        private String liveTitle;
        private String liveThumbnailImageUrl;
        private int concurrentUserCount;
        private String openDate;
        private boolean adult;
        private List<String> tags;
        private String categoryType;
        private String liveCategory;
        private String liveCategoryValue;
        private String channelId;
        private String channelName;
        private String channelImageUrl;
    }
}


