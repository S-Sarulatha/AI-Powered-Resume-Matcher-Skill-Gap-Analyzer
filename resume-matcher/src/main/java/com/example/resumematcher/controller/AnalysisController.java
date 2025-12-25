package com.example.resumematcher.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumematcher.entity.AnalysisResult;
import com.example.resumematcher.repository.AnalysisResultRepository;
import com.example.resumematcher.service.EligibilityService;
import com.example.resumematcher.service.RecommendationService;
import com.example.resumematcher.service.ResumeParserService;
import com.example.resumematcher.service.ScoringService;
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

    @Autowired
    private ScoringService scoringService;

    @Autowired
    private EligibilityService eligibilityService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private AnalysisResultRepository repository;

    @PostMapping("/analyze")
    public Map<String, Object> analyzeResumes(
            @RequestParam("resumes") MultipartFile[] resumes,
            @RequestParam("jobDescription") String jobDescription
    ) throws Exception {

        Set<String> jdSkills =
                extractor.extractJDSkills(jobDescription.toLowerCase());

        Map<String, Object> response = new HashMap<>();
        Map<String, Map<String, Object>> resultPerResume = new HashMap<>();

        for (MultipartFile resume : resumes) {
            try {
                // ---------- Parse Resume ----------
                String resumeText = parser.extractText(resume);

                Set<String> resumeSkills =
                        extractor.extractSkills(resumeText);

                Map<String, Set<String>> gaps =
                        gapService.analyze(resumeSkills, jdSkills);

                double experience =
                        extractor.extractExperience(resumeText);

                Set<String> education =
                        extractor.extractEducation(resumeText);

                Set<String> projects =
                        extractor.extractProjects(resumeText);

                // ---------- Scoring ----------
                int score = scoringService.calculateFinalScore(
                        gaps.get("matched_skills"),
                        jdSkills,
                        experience,
                        education
                );

                // ---------- Eligibility ----------
                List<String> eligibilityReasons =
                        eligibilityService.checkEligibility(
                                score,
                                experience,
                                gaps.get("missing_skills")
                        );

                // ---------- Skill Strength ----------
                Map<String, String> skillStrength =
                        extractor.analyzeSkillStrength(resumeText, jdSkills);

                // ---------- Recommendations ----------
                List<String> recommendations =
                        recommendationService.suggestImprovements(
                                gaps.get("missing_skills")
                        );

                // ---------- Save to DB ----------
                AnalysisResult entity = new AnalysisResult();
                entity.setCandidateName(resume.getOriginalFilename());
                entity.setMatchScore(score);
                entity.setMatchedSkills(String.join(",", gaps.get("matched_skills")));
                entity.setMissingSkills(String.join(",", gaps.get("missing_skills")));
                entity.setExperience(experience);
                entity.setSkillStrength(skillStrength.toString());
                entity.setAnalyzedAt(LocalDateTime.now());

                repository.save(entity);

                // ---------- Response ----------
                Map<String, Object> singleResult = new HashMap<>();
                singleResult.put("match_score", score);
                singleResult.put("matched_skills", gaps.get("matched_skills"));
                singleResult.put("missing_skills", gaps.get("missing_skills"));
                singleResult.put("skill_strength", skillStrength);
                singleResult.put("experience_years", experience);
                singleResult.put("education", education);
                singleResult.put("projects", projects);
                singleResult.put("eligible", eligibilityReasons.isEmpty());
                singleResult.put("reasons", eligibilityReasons);
                singleResult.put("recommendations", recommendations);

                resultPerResume.put(
                        resume.getOriginalFilename(),
                        singleResult
                );

            } catch (Exception e) {
                Map<String, Object> errorResult = new HashMap<>();
                errorResult.put("error", "Failed to parse resume");
                resultPerResume.put(resume.getOriginalFilename(), errorResult);
            }
        }

        response.put("results", resultPerResume);
        return response;
    }

    @GetMapping("/ranked-candidates")
    public List<AnalysisResult> getRankedCandidates() {
        return repository.findAllByOrderByMatchScoreDesc();
    }
}
