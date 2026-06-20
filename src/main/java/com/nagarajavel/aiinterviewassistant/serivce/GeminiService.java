package com.nagarajavel.aiinterviewassistant.serivce;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String askGemini(String question) {

        try {

            Client client = Client.builder()
                    .apiKey(apiKey)
                    .build();

            return client.models.generateContent(
                    "gemini-2.5-flash",
                    question,
                    null
            ).text();

        }catch (Exception e) {
            return """
    Tell me about yourself.
    Why should we hire you?
    What are your strengths?
    What are your weaknesses?
    Where do you see yourself in 5 years?
    """;
        }
    }
}