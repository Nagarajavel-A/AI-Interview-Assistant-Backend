package com.nagarajavel.aiinterviewassistant.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private int atsScore;
    private List<String> skills;
    private List<String> missingSkills;
    private List<String> suggestions;

    public ResumeAnalysisResponse(int atsScore,
                                  List<String> skills,
                                  List<String> missingSkills,
                                  List<String> suggestions) {
        this.atsScore = atsScore;
        this.skills = skills;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}