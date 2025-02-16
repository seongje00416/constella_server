package com.example.eunftel_server.service;

import com.example.eunftel_server.common.PageResponse;
import com.example.eunftel_server.domain.dto.CafeJson;
import com.example.eunftel_server.domain.dto.ChzzkJson;
import com.example.eunftel_server.domain.dto.MemberNotice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class CafeApiService {

    private final String BASE_URL = "https://openapi.naver.com/v1";
    private final String CLIENT_ID = "8IM9sSbCtdAGyVAwpBcR";
    private final String CLIENT_SECRET = "MFhHjK2USm";

    private final WebClient webClient;

    public CafeApiService( WebClient.Builder webClientBuilder ) {
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader("X-Naver-Client-Id", CLIENT_ID)
                .defaultHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();
    }

    public void getMemberNotice( String member ){
        try{
            Mono<CafeJson> response = webClient.get()
                    .uri( uriBuilder -> uriBuilder
                            .path( "/search/cafearticle.json" )
                            .queryParam( "query", member )
                            .queryParam( "display", 50 )
                            .queryParam( "start", 1 )
                            .queryParam( "sort", "date" )
                            .build())
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(CafeJson.class)
                            .doOnError(error -> {
                                System.out.println("Error occurred: " + error.getMessage());  // 에러 로깅 추가
                            })
                            .delayElement(Duration.ofMillis(1000))
                            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                                    .filter(throwable -> throwable instanceof WebClientResponseException)
                            );
            CafeJson httpResult = response.block();
            if (httpResult != null) {
                System.out.println("Response received: " + httpResult);  // 응답 로깅 추가
            }
            for( CafeJson.Item item : httpResult.getItems() ){
                if( item.getCafename().equals("스텔라이브") ) System.out.println( item.getTitle() );
            }
        } catch (WebClientResponseException e) {
            System.out.println("WebClient error: " + e.getMessage());  // 에러 로깅 추가
            Mono.delay(Duration.ofSeconds(2)).block();  // 2초 대기
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());  // 에러 로깅 추가
            Mono.delay(Duration.ofSeconds(2)).block();  // 2초 대기
        }
    }


}
