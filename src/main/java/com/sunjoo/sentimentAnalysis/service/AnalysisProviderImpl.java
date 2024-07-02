package com.sunjoo.sentimentAnalysis.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import com.sunjoo.sentimentAnalysis.dto.AnalysisRequest;
import com.sunjoo.sentimentAnalysis.dto.AnalysisResult;
import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Log4j2
public class AnalysisProviderImpl implements AnalysisProvider{
    // 텍스트 분석
    private static final String TEXT_API_URL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
    // 얼굴 분석

    private static final String CLIENT_ID_HEADER = "X-NCP-APIGW-API-KEY-ID";
    private static final String CLIENT_SECRET_HEADER = "X-NCP-APIGW-API-KEY";

    @Value("${security.api.id}")
    private String CLIENT_ID;
    @Value("${security.api.key}")
    private String CLIENT_SECRET;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Sentiment analyze(AnalysisRequest request) throws IOException {
        final AnalysisResult textResult = getTextResult(request.getTextExpression());

        return textResult.getSentiment();
    }

    // get Text Result
    private AnalysisResult getTextResult(final String text) {
        final HttpHeaders headers = makeHeaders(MediaType.APPLICATION_JSON);

        final JSONObject body = new JSONObject();
        body.put("content", text);

        log.info("Request Body: {}", body.toString());
        log.info("Headers: {}", headers);

        HttpEntity<String> httpEntity = new HttpEntity<>(body.toString(), headers);
        final String result = restTemplate.postForObject(TEXT_API_URL, httpEntity, String.class);

        log.info("Response Body: {}", result);

        final JSONObject jsonObject = new JSONObject(result);
        final JSONObject confidence = jsonObject.getJSONObject("document").getJSONObject("confidence");
        final double positive = confidence.getDouble("positive");
        final double neutral = confidence.getDouble("neutral");

        return AnalysisResult.of(positive, neutral);
    }



    // naver api요청 -> make header
    private HttpHeaders makeHeaders(final MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CLIENT_ID_HEADER, CLIENT_ID);
        headers.add(CLIENT_SECRET_HEADER, CLIENT_SECRET);
        headers.setContentType(mediaType);

        log.info("makeeeeee Headers: {}", headers);
        return headers;
    }


}
