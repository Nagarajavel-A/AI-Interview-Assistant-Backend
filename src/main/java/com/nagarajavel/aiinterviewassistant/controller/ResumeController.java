package com.nagarajavel.aiinterviewassistant.controller;

import com.nagarajavel.aiinterviewassistant.dto.ResumeAnalysisResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.nagarajavel.aiinterviewassistant.serivce.GeminiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ResumeController {
    private final GeminiService geminiService;
    private static List<String> extractedSkills = new java.util.ArrayList<>();

    public ResumeController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/resume/analyze")
    public ResumeAnalysisResponse analyzeResume() {

        return new ResumeAnalysisResponse(
                82,
                List.of("Java", "Spring Boot", "React"),
                List.of("Docker", "AWS"),
                List.of(
                        "Add more projects",
                        "Add certifications",
                        "Improve resume summary"
                )
        );
    }
    @GetMapping("/resume/questions")
    public String resumeQuestions() {

        if (extractedSkills.isEmpty()) {
            extractedSkills.add("Java");
            extractedSkills.add("Spring Boot");
            extractedSkills.add("React");
            extractedSkills.add("MySQL");
        }

        String prompt = """
        Generate 5 interview questions based on these skills:
        """ + String.join(", ", extractedSkills) + """

        Return only questions.
        One question per line.
        Do not add numbering.
        """;

        return geminiService.askGemini(prompt);
    }
    @PostMapping("/resume/upload")
    public String uploadResume(
            @RequestParam("resume") MultipartFile file) {

        try {

            PDDocument document =
                    Loader.loadPDF(file.getBytes());

            PDFTextStripper pdfStripper =
                    new PDFTextStripper();

            String text = pdfStripper.getText(document);

            String lowerText = text.toLowerCase();

            extractedSkills.clear();

            if (lowerText.contains("java"))
                extractedSkills.add("Java");

            if (lowerText.contains("spring"))
                extractedSkills.add("Spring Boot");

            if (lowerText.contains("react"))
                extractedSkills.add("React");

            if (lowerText.contains("mysql"))
                extractedSkills.add("MySQL");

            if (lowerText.contains("docker"))
                extractedSkills.add("Docker");

            if (lowerText.contains("aws"))
                extractedSkills.add("AWS");

            document.close();

            return "Resume uploaded successfully";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}