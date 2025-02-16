package com.example.eunftel_server.service;

import com.example.eunftel_server.domain.dto.ChzzkJson;
import com.example.eunftel_server.domain.dto.response.GetChzzkLiveOn;
import com.example.eunftel_server.domain.type.ChzzkCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class ChzzkApiService {
    private final String BASE_URL = "https://openapi.chzzk.naver.com/open/v1";
    private final String CLIENT_ID = "9f0fb104-4229-4f09-9232-bdfe3085f563";
    private final String CLIENT_SECRET = "7AMvOmPiF5FrU2-NYUbDnK7Xs-cCxoR_sFktb3-f4KY";
    private final String CONTENT_TYPE = "application/json";

    private final WebClient webClient;

    public ChzzkApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader("Client-Id", CLIENT_ID)
                .defaultHeader("Client-Secret", CLIENT_SECRET)
                .defaultHeader("Content-Type", CONTENT_TYPE)
                .build();
    }

    public GetChzzkLiveOn getLiveStreams(int size) {
        // 라이브 목록을 돌아보며 멤버들을 찾음 == channelId를 통한 검색
        // 모든 방송을 찾아보기엔 너무 과한 반복이 되므로 시청자 수 200명을 제한
        GetChzzkLiveOn.GetChzzkLiveOnBuilder data = GetChzzkLiveOn.builder();

        boolean flag = true;
        AtomicReference<String> next = new AtomicReference<>(null);
        Map<String, Boolean> result = new HashMap<>();

        List<String> memberIdList = Arrays.stream(ChzzkCode.values())
                .map(ChzzkCode::getCode)
                .collect(Collectors.toList());

        while (flag) {
            try {
                Mono<ChzzkJson> response = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/lives")
                                .queryParam("size", size)
                                .queryParam("next", next.get())
                                .build())
                        .exchange()
                        .flatMap(clientResponse -> {
                            if (clientResponse.statusCode() == HttpStatus.BAD_REQUEST) {
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(errorBody -> Mono.error(new WebClientResponseException(
                                                400, "Bad Request",
                                                clientResponse.headers().asHttpHeaders(),
                                                errorBody.getBytes(),
                                                null
                                        )));
                            }
                            return clientResponse.bodyToMono(ChzzkJson.class);
                        })
                        .delayElement(Duration.ofMillis(1000))  // 1초 딜레이
                        .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                                .filter(throwable -> throwable instanceof WebClientResponseException)
                        );

                ChzzkJson httpResult = response.block();
                if (httpResult == null || httpResult.getContent() == null ||
                        httpResult.getContent().getData() == null ||
                        httpResult.getContent().getData().isEmpty()) {
                    continue;
                }

                List<ChzzkJson.LiveStream> liveList = httpResult.getContent().getData();

                Set<String> set = new HashSet<>();
                for (ChzzkJson.LiveStream live : liveList) {
                    if (live.getChannelId() != null) {
                        set.add(live.getChannelId());
                    }
                }

                for (String member : memberIdList) {
                    if (set.contains(member)) {
                        result.put(member, true);
                    }
                }

                if (liveList.size() < 20 || liveList.get(19).getConcurrentUserCount() < 200) {
                    flag = false;
                } else {
                    next.set(httpResult.getContent().getPage().getNext());
                }

            } catch (WebClientResponseException e) {
                Mono.delay(Duration.ofSeconds(2)).block();  // 2초 대기
            } catch (Exception e) {
                Mono.delay(Duration.ofSeconds(2)).block();  // 2초 대기
            }
        }
//        while( flag ){
//            Mono<ChzzkJson> response = webClient.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/lives")
//                            .queryParam("size", size)
//                            .queryParam( "next", next.get() )
//                            .build())
//                    .exchange()
//                    .flatMap(clientResponse -> {
//                        if (clientResponse.statusCode() == HttpStatus.BAD_REQUEST) {
//                            return clientResponse.bodyToMono(String.class)
//                                    .flatMap(errorBody -> {
//                                        return Mono.error(new WebClientResponseException(
//                                                400, "Bad Request",
//                                                clientResponse.headers().asHttpHeaders(),
//                                                errorBody.getBytes(),
//                                                null
//                                        ));
//                                    });
//                        }
//                        return clientResponse.bodyToMono(ChzzkJson.class);
//                    })
//                    .delayElement(Duration.ofMillis(300))
//                    .retry(3);
//            ChzzkJson httpResult = response.block();
//            List<ChzzkJson.LiveStream> liveList = httpResult.getContent().getData();
//
//            Set<String> set = new HashSet<>();
//            for (ChzzkJson.LiveStream live : liveList) { set.add(live.getChannelId()); }
//
//            for (String member : memberIdList) {
//                if( set.contains(member) )  result.put(member, true );
//            }
//            if( liveList.get(19).getConcurrentUserCount() < 200 ) flag = false;
//            else next.set( httpResult.getContent().getPage().getNext() );
//        }
        // 치지직 코드:멤버이름 매핑된 Map
        Map<String, String> codeNameMap = Arrays.stream(ChzzkCode.values())
                .collect(Collectors.toMap(
                        ChzzkCode::getCode,
                        ChzzkCode::getName
                ));

        // result에 있으면 true, 없으면 false
        for (Map.Entry<String, String> entry : codeNameMap.entrySet()) {
            String code = entry.getKey();
            String name = entry.getValue();
            switch( name ){
                case "yuni" :
                    if( result.get( code ) != null ) data.yuniLive( true );
                    else data.yuniLive( false );
                    break;
                case "hina" :
                    if( result.get( code ) != null ) data.hinaLive( true );
                    else data.hinaLive( false );
                    break;
                case "lize" :
                    if( result.get( code ) != null ) data.lizeLive( result.get( code ) );
                    else data.lizeLive( false );
                    break;
                case "mashiro" :
                    if( result.get( code ) != null ) data.mashiroLive( result.get( code ) );
                    else data.mashiroLive( false );
                    break;
                case "tabi" :
                    if( result.get( code ) != null ) data.tabiLive( result.get( code ) );
                    else data.tabiLive( false );
                    break;
                case "rin" :
                    if( result.get( code ) != null ) data.rinLive( result.get( code ) );
                    else data.rinLive( false );
                    break;
                case "shibuki":
                    if( result.get( code ) != null ) data.shibukiLive( result.get( code ) );
                    else data.shibukiLive( false );
                    break;
                case "riko" :
                    if( result.get( code ) != null ) data.rikoLive( result.get( code ) );
                    else data.rikoLive( false );
                    break;
                case "nana" :
                    if( result.get( code ) != null ) data.nanaLive( result.get( code ) );
                    else data.nanaLive( false );
                    break;
                default:
                    break;
            }
        }
        return data.build();
    }
}
