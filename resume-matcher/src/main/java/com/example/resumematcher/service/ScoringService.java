package com.example.resumematcher.service;

import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public int calculateFinalScore(
            Set<String> matchedSkills,
            Set<String> jdSkills,
            double experience,
            Set<String> education
    ) {

        // Skill Score
        double skillScore = jdSkills.isEmpty()
                ? 0
                : ((double) matchedSkills.size() / jdSkills.size()) * 100;

        // Experience Score (max 5 years)
        double experienceScore = Math.min(experience / 5.0, 1.0) * 100;

        // Education Score
        double educationScore = education.isEmpty() ? 0 : 100;

        // Weighted Score
        double finalScore =
                (skillScore * 0.5) +
                (experienceScore * 0.3) +
                (educationScore * 0.2);

        return (int) finalScore;
    }
}
