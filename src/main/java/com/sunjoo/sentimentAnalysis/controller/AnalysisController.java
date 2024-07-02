package com.sunjoo.sentimentAnalysis.controller;

import com.sunjoo.sentimentAnalysis.client.UserClient;
import com.sunjoo.sentimentAnalysis.dto.*;
import com.sunjoo.sentimentAnalysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class AnalysisController {

    private final AnalysisService analysisService;
    private final UserClient userClient;

    @GetMapping("/{analysis_id}")
    public ResponseEntity<AnalysisResponse> findAnalysis(@PathVariable(value = "analysis_id") Long analysisId, @RequestHeader("Authorization") String token) {
        final AnalysisResponse analysis = analysisService.findAnalysisById(analysisId, token);

        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AnalysisHistory>> findHistoriesByUserId(@RequestHeader("Authorization") String token) {
        log.info("token값입니다: " + token);
        // userId 조회
        UserInfoResponse userInfoResponse = userClient.getUserInfo(token);
        UserDTO user = userInfoResponse.getResult();

        log.info("idididid------" +user.getId());

        final List<AnalysisHistory> histories = analysisService.findHistoriesByUserId(token);

        return ResponseEntity.ok(histories);
    }

    @PostMapping("/sources")
    public ResponseEntity<AnalysisCreatedResponse> createAnalysisSource(@RequestHeader("Authorization") String token,
                                                                        @RequestBody AnalysisRequest sourceRequest) {


        log.info("sources----------" + token);
        // userId 조회
        UserInfoResponse userInfoResponse = userClient.getUserInfo(token);
        UserDTO user = userInfoResponse.getResult();

        final AnalysisResponse analysisResponse = analysisService.postAnalysis(user.getUserNo(), sourceRequest, token);

        return ResponseEntity.ok(new AnalysisCreatedResponse(analysisResponse.getId()));
    }


}

