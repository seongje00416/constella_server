package com.example.eunftel_server.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChzzkCode {
    YUNI("45e71a76e949e16a34764deb962f9d9f", "yuni"),
    HINA("b044e3a3b9259246bc92e863e7d3f3b8", "hina"),
    LIZE("4325b1d5bbc321fad3042306646e2e50", "lize"),
    MASHIRO("4515b179f86b67b4981e16190817c580", "mashiro"),
    TABI("a6c4ddb09cdb160478996007bff35296", "tabi"),
    SHIBUKI("64d76089fba26b180d9c9e48a32600d9","shibuki"),
    RIN("516937b5f85cbf2249ce31b0ad046b0f", "rin"),
    NANA("4d812b586ff63f8a2946e64fa860bbf5", "nana"),
    RIKO("8fd39bb8de623317de90654718638b10", "riko");

    private final String code;
    private final String name;
}
