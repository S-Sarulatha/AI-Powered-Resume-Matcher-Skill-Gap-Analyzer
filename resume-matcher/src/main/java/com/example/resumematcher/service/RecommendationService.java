package com.example.resumematcher.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public List<String> suggestImprovements(Set<String> missingSkills) {

        List<String> suggestions = new ArrayList<>();

        if (missingSkills == null || missingSkills.isEmpty()) {
            return suggestions;
        }

        for (String skill : missingSkills) {
            suggestions.add(
                "Consider adding hands-on experience or a project related to " + skill
            );
        }

        return suggestions;
    }
}

