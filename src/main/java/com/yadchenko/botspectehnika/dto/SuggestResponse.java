package com.yadchenko.botspectehnika.dto;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResponse {
    private List<SuggestResult> results;

    @Data
    public static class SuggestResult {
        private SuggestText title;
        private SuggestText subtitle;
        private List<String> tags;
    }

    @Data
    public static class SuggestText {
        private String text;
        private List<SuggestHighlight> hl;
    }

    @Data
    public static class SuggestHighlight {
        private int begin;
        private int end;
    }
}
