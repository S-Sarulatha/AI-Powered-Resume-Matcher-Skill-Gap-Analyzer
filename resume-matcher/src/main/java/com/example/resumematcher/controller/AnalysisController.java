package com.example.resumematcher.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumematcher.service.ResumeParserService;
import com.example.resumematcher.service.SkillExtractorService;
import com.example.resumematcher.service.SkillGapService;

@RestController
@RequestMapping("/api")
public class AnalysisController {

    @Autowired
    private ResumeParserService parser;

    @Autowired
    private SkillExtractorService extractor;

    @Autowired
    private SkillGapService gapService;

    @PostMapping("/analyze")
    public Map<String, Object> analyzeResumes(
            @RequestParam("resumes") MultipartFile[] resumes,
            @RequestParam("jobDescription") String jobDescription
    ) throws Exception {
        Set<String> jdSkills = extractor.extractJDSkills(jobDescription.toLowerCase());

        Map<String, Object> response = new HashMap<>();
        Map<String, Map<String, Object>> resultPerResume = new HashMap<>();

        for (MultipartFile resume : resumes) {
            String resumeText = parser.extractText(resume);
            Set<String> resumeSkills = extractor.extractSkills(resumeText);
            Map<String, Set<String>> gaps = gapService.analyze(resumeSkills, jdSkills);

            int matchedCount = gaps.get("matched_skills").size();
            int score = jdSkills.isEmpty() ? 0 : (matchedCount * 100 / jdSkills.size());

            Map<String, Object> singleResult = new HashMap<>();
            singleResult.put("match_score", score);
            singleResult.put("matched_skills", gaps.get("matched_skills"));
            singleResult.put("missing_skills", gaps.get("missing_skills"));

            resultPerResume.put(resume.getOriginalFilename(), singleResult);
        }

        response.put("results", resultPerResume);
        return response;
    }
}
