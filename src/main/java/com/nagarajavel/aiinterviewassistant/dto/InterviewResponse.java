package com.nagarajavel.aiinterviewassistant.dto;

import java.util.List;

public class InterviewResponse {

    private int score;
    private List<String> strengths;
    private List<String> weaknesses;
    private String suggestion;

    public InterviewResponse(
            int score,
            List<String> strengths,
            List<String> weaknesses,
            String suggestion) {

        this.score = score;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.suggestion = suggestion;
    }

    public int getScore() {
        return score;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public String getSuggestion() {
        return suggestion;
    }
}