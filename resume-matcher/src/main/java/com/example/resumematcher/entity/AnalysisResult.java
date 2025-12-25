package com.example.resumematcher.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;
    private Integer matchScore;

    @Column(length = 2000)
    private String matchedSkills;

    @Column(length = 2000)
    private String missingSkills;

    private double experience;
    private LocalDateTime analyzedAt;
    // Getters & Setters
    public Long getId() { return id; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public int getMatchScore() { return matchScore; }
    public void setMatchScore(int matchScore) { this.matchScore = matchScore; }

    public String getMatchedSkills() { return matchedSkills; }
    public void setMatchedSkills(String matchedSkills) { this.matchedSkills = matchedSkills; }

    public String getMissingSkills() { return missingSkills; }
    public void setMissingSkills(String missingSkills) { this.missingSkills = missingSkills; }

    public double getExperience() { return experience; }
    public void setExperience(double experience) { this.experience = experience; }

    public LocalDateTime getAnalyzedAt() { return analyzedAt; }
    public void setAnalyzedAt(LocalDateTime analyzedAt) { this.analyzedAt = analyzedAt; }
}
