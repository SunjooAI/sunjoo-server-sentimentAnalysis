package com.sunjoo.sentimentAnalysis.dto;

import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnalysisResult {
    private double positive;

    public static AnalysisResult of(final String emotionString, final double confidence) {
        final AnalysisResult analysisResult = new AnalysisResult();
        if (emotionString.equals("smile")) {
            analysisResult.setPositive(confidence);
        } else if (emotionString.equals("neutral")) {
            analysisResult.setPositive(confidence / 2);
        } else if (emotionString.equals("laugh")) {
            analysisResult.setPositive(confidence);
        } else if (emotionString.equals("taliking")) {
            analysisResult.setPositive(confidence / 2);
        }

        return analysisResult;
    }

    public static AnalysisResult of(final double positive, final double neutral) {
        return new AnalysisResult(positive + neutral / 2);
    }

    public Sentiment getSentiment() {
        if (positive < 20) {
            return Sentiment.SAD2;
        } else if (positive < 40) {
            return Sentiment.SAD1;
        } else if (positive < 60) {
            return Sentiment.MEDIAN;
        } else if (positive < 80) {
            return Sentiment.Happy1;
        } else {
            return Sentiment.HAPPY2;
        }
    }
}