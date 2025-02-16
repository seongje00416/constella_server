package com.example.eunftel_server.common;

import org.springframework.data.domain.Page;

public class PageUtil {
    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
    }
}
