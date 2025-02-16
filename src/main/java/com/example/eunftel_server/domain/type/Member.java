package com.example.eunftel_server.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Member {
    AYATSUNO_YUNI( "아야츠노 유니", 1 ),
    AIRI_KANNA( "아이리 칸나", 1 ),
    SHIRAYUKI_HINA( "시라유키 히나", 2 ),
    NENEKO_MASHIRO( "네네코 마시로", 2 ),
    AKANE_LIZE( "아카네 리제", 2 ),
    ARAHASHI_TABI( "아라하시 타비", 2 ),
    TENKO_SHIBUKI( "텐코 시부키", 3 ),
    AOKUMO_RIN( "아오쿠모 린", 3 ),
    HANAKO_NANA( "하나코 나나", 3 ),
    YUZUHA_RIKO( "유즈하 리코", 3 );

    private final String name;
    private final int season;
}

