package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.config.BotConfig;
import com.yadchenko.botspectehnika.dto.SuggestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BotConfig botConfig;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://suggest-maps.yandex.ru/v1/suggest")
            .build();

    public Mono<SuggestResponse> search(String query) {
        return webClient.get().uri(uri -> uri
                        .queryParam("apikey", botConfig.getApikey())
                        .queryParam("text", query)
                        .queryParam("lang", "ru_RU")
                        .queryParam("types", "locality")
                        .queryParam("bbox", "23.1783,51.2577~32.7768,56.1719")
                        .queryParam("strict_bounds", "1")
                        .queryParam("results", "3")
                        .build())
                .retrieve()
                .bodyToMono(SuggestResponse.class);
    }
}
