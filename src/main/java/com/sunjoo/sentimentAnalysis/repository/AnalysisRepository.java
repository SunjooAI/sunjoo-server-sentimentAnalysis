package com.sunjoo.sentimentAnalysis.repository;

import com.sunjoo.sentimentAnalysis.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    List<Analysis> findByUserId(Long userId);
}
