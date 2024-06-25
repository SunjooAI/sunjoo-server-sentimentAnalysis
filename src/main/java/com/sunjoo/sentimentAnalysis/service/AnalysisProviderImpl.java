package com.sunjoo.sentimentAnalysis.service;

import com.sunjoo.sentimentAnalysis.dto.AnalysisRequest;
import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AnalysisProviderImpl implements AnalysisProvider{
    // 텍스트 분석
    private static final String TEXT_API_URL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
    // 얼굴 분석

    private static final String CLIENT_ID_HEADER = "X-NCP-APIGW-API-KEY-ID";
    private static final String CLIENT_SECRET_HEADER = "X-NCP-APIGW-API-KEY";


    @Override
    public Sentiment analyze(AnalysisRequest reauest) throws IOException {
        return null;
    }

    // get Text Result

}
