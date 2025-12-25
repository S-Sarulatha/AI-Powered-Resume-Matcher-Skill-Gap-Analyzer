package com.example.resumematcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class EligibilityService {

    public List<String> checkEligibility(
            int score,
            double experience,
            Set<String> missingSkills
    ) {

        List<String> reasons = new ArrayList<>();

        if (score < 60)
            reasons.add("Match score below 60%");

        if (experience < 1)
            reasons.add("Less than 1 year experience");

        if (!missingSkills.isEmpty())
            reasons.add("Missing required skills: " + missingSkills);

        return reasons;
    }
}
