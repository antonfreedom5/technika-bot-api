package com.yadchenko.botspectehnika.controllers;

import com.yadchenko.botspectehnika.dto.SuggestResponse;
import com.yadchenko.botspectehnika.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/search")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/city")
    public Mono<SuggestResponse> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
