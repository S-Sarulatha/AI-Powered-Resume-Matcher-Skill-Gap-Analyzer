package com.example.resumematcher.service;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;

@Service
public class SkillExtractorService {

    private static final List<String> SKILLS = List.of(
        "java", "spring boot", "sql", "html",
        "css", "javascript", "docker", "aws",
        "machine learning", "nlp" ,"oops"
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
 // Extract Years of Experience
    public double extractExperience(String text) {
        if (text == null) return 0;
        Pattern pattern = Pattern.compile("\\b(\\d+(\\.\\d+)?)\\+?\\s*years?\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        double maxExp = 0;
        while (matcher.find()) {
            double exp = Double.parseDouble(matcher.group(1));
            if (exp > maxExp) maxExp = exp;
        }
        return maxExp;
    }


    // Extract Education Keywords
    public Set<String> extractEducation(String text) {
        List<String> eduKeywords = List.of(
            "b.tech", "m.tech", "mba", "b.sc", "m.sc", "b.com", "m.com", "phd", "ug", "pg"
        );
        Set<String> foundEdu = new HashSet<>();
        text = text.toLowerCase();
        for (String edu : eduKeywords) {
            if (text.contains(edu)) foundEdu.add(edu.toUpperCase());
        }
        return foundEdu;
    }

    // Extract Projects / Tools
 // Extract ONLY Project Titles
    public Set<String> extractProjects(String text) {
        Set<String> projects = new HashSet<>();

        if (text == null) return projects;

        String[] lines = text.split("\\r?\\n");
        boolean inProjectSection = false;

        for (String line : lines) {
            line = line.trim().toLowerCase();

            // Detect projects section
            if (line.matches(".*projects.*")) {
                inProjectSection = true;
                continue;
            }

            // Stop when another section starts
            if (inProjectSection && line.matches(".*(education|skills|experience|certification|summary).*")) {
                break;
            }

            if (inProjectSection) {

                // Skip description lines
                if (line.startsWith("description")
                        || line.contains("developed")
                        || line.contains("designed")
                        || line.contains("implemented")
                        || line.contains("using")
                        || line.length() > 80) {
                    continue;
                }

                // Skip tools-only lines
                if (line.matches(".*(html|css|javascript|java|sql|spring|blockchain).*")
                        && line.split(" ").length <= 3) {
                    continue;
                }

                // Remove bullets, numbers
                line = line.replaceAll("^[•\\-\\d\\.]+", "").trim();

                // Accept only title-like lines
                if (line.length() >= 5 && line.length() <= 60) {
                    projects.add(capitalize(line));
                }
            }
        }

        return projects;
    }

    // Helper method (ADD BELOW)
    private String capitalize(String text) {
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1))
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }
    public Map<String, String> analyzeSkillStrength(String text, Set<String> jdSkills) {

        Map<String, String> strengthMap = new HashMap<>();
        text = text.toLowerCase();

        for (String skill : jdSkills) {
            int count = text.split(skill, -1).length - 1;

            if (count == 0) {
                strengthMap.put(skill, "MISSING");
            } else if (count == 1) {
                strengthMap.put(skill, "WEAK");
            } else {
                strengthMap.put(skill, "STRONG");
            }
        }
        return strengthMap;
    }

   
}