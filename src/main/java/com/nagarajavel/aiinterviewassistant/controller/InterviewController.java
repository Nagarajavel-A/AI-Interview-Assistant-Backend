package com.nagarajavel.aiinterviewassistant.controller;
import com.nagarajavel.aiinterviewassistant.serivce.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.nagarajavel.aiinterviewassistant.dto.InterviewRequest;
import com.nagarajavel.aiinterviewassistant.dto.InterviewResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@CrossOrigin(origins = "http://localhost:5173")
    @RestController
    public class InterviewController {
        private final GeminiService geminiService;
        public InterviewController(GeminiService geminiService) {
            this.geminiService = geminiService;}

        @GetMapping("/interview/hr")
        public String hrQuestions() {

            String prompt = """
                    Generate 5 HR interview questions.
                    Return ONLY the questions.
                    Each question must be on a new line.
                    Do not include numbering explanations or headings.
            """;

            return geminiService.askGemini(prompt);
        }
    @GetMapping("/technical")
    public String technicalQuestions() {
        String prompt = """
    Generate 5 Java Spring Boot technical interview questions.
    Return only questions.
    One question per line.
    """;

        return geminiService.askGemini(prompt);
    }
    @PostMapping("/interview/evaluate")
    public InterviewResponse evaluateAnswer(
            @RequestBody InterviewRequest request) {

        int score;

        if(request.getAnswer().length() < 20){
            score = 5;
        }else if(request.getAnswer().length() < 50){
            score = 7;
        }else{
            score = 9;
        }

        return new InterviewResponse(
                score,
                java.util.List.of(
                        "Good explanation",
                        "Clear answer"
                ),
                java.util.List.of(
                        "Need more technical details"
                ),
                "Add examples and explain concepts in depth."
        );
    }
    @GetMapping("/interview/report")
    public String interviewReport() {

        return """
            Overall Performance: Excellent

            Strengths:
            - Good communication
            - Strong Java basics
            - Clear explanations

            Improvements:
            - Improve Spring Boot knowledge
            - Learn System Design

            Readiness Score: 85%
            """;
    }
    }

