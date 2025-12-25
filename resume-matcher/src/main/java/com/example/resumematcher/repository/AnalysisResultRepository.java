package com.example.resumematcher.repository;

import com.example.resumematcher.entity.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
	
    List<AnalysisResult> findAllByOrderByMatchScoreDesc();
}
