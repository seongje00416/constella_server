package com.example.eunftel_server.domain.dto;

import lombok.Data;

@Data
public class StreamStatus {
    private boolean openLive;
    private Channel channel;

    @Data
    public static class Channel {
        private String channelId;
        private String channelName;
        private String channelImageUrl;
    }
}
