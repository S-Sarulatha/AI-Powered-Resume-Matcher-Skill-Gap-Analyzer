package com.example.resumematcher.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class SkillExtractorService {

    private static final List<String> SKILLS = List.of(
        "java", "spring boot", "sql", "html",
        "css", "javascript", "docker", "aws",
        "machine learning", "nlp"
    );

    public Set<String> extractSkills(String text) {
        Set<String> found = new HashSet<>();
        for (String skill : SKILLS) {
            if (text.contains(skill)) {
                found.add(skill);
            }
        }
        return found;
    }
    public Set<String> extractJDSkills(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        Set<String> jdSkills = new HashSet<>();
        for (String word : words) {
            jdSkills.add(word);
        }
        return jdSkills;
    }
}
