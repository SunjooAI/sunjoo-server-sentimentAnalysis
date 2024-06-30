package com.sunjoo.sentimentAnalysis.dto;

import com.sunjoo.sentimentAnalysis.entity.Analysis;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AnalysisResponses {
    private final List<AnalysisResponse> list;

    public static AnalysisResponses of(final List<AnalysisResponse> responses) {
        return new AnalysisResponses(responses);
    }
}
