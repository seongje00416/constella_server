package com.example.eunftel_server.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NaverCafeCode {
    YUNI( "", 0 ),
    HINA( "", 149 ),
    MASHIRO( "", 150 ),
    LIZE( "", 151 ),
    TABI( "", 152 ),
    RIN( "", 0 ),
    NANA( "", 0 ),
    SHIBUKI( "", 0 ),
    RIKO( "", 0 );

    private final String name;
    private final int menuId;
}
