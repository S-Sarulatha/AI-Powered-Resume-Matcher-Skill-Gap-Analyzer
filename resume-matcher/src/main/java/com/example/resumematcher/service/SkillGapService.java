package com.example.resumematcher.service;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class SkillGapService {

    public Map<String, Set<String>> analyze(Set<String> resume, Set<String> jd) {

        Set<String> matched = new HashSet<>(resume);
        matched.retainAll(jd);
        Set<String> missing = new HashSet<>(jd);
        missing.removeAll(resume);

        Map<String, Set<String>> result = new HashMap<>();
        result.put("matched_skills", matched);
        result.put("missing_skills", missing);

        return result;
    }
    

}