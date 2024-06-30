package com.sunjoo.sentimentAnalysis.controller;

import com.sunjoo.sentimentAnalysis.dto.AnalysisResponse;
import com.sunjoo.sentimentAnalysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/{analysis_id}")
    public ResponseEntity<AnalysisResponse> findAnalysis(@PathVariable(value = "analysis_id") Long analysisId, @RequestHeader("Authorization") String token) {
        final AnalysisResponse analysis = analysisService.findAnalysisById(analysisId, token);

        return ResponseEntity.ok(analysis);
    }
}

