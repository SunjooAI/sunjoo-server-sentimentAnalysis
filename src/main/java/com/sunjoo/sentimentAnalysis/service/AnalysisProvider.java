package com.sunjoo.sentimentAnalysis.service;

import com.sunjoo.sentimentAnalysis.dto.AnalysisRequest;
import com.sunjoo.sentimentAnalysis.entity.Sentiment;

import java.io.IOException;

public interface AnalysisProvider {
    Sentiment analyze(AnalysisRequest reauest) throws IOException;
}
