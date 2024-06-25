package com.sunjoo.sentimentAnalysis.dto;

import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AnalysisHistory {
    private final Long resultId;
    private final LocalDateTime date;
    private final Sentiment sentiment;

    public static AnalysisHistory from(final AnalysisResponse analysis) {
        return new AnalysisHistory(analysis.getId(), analysis.getDate(), analysis.getSentiment());
    }
}
