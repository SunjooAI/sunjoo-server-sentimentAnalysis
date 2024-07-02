package com.sunjoo.sentimentAnalysis.service;

import com.sunjoo.sentimentAnalysis.dto.*;
import com.sunjoo.sentimentAnalysis.client.DrinkClient;
import com.sunjoo.sentimentAnalysis.client.UserClient;
import com.sunjoo.sentimentAnalysis.dto.AnalysisHistory;
import com.sunjoo.sentimentAnalysis.dto.AnalysisRequest;
import com.sunjoo.sentimentAnalysis.dto.AnalysisResponse;
import com.sunjoo.sentimentAnalysis.dto.UserInfoResponse;
import com.sunjoo.sentimentAnalysis.entity.Analysis;
import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import com.sunjoo.sentimentAnalysis.repository.AnalysisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class AnalysisService {

    private final DrinkClient drinkClient;
    private final UserClient userClient;
    private final AnalysisProvider analysisProvider;
    private final AnalysisRepository analysisRepository;

    public List<AnalysisHistory> findHistoriesByUserId(String token) {
        // token을 사용하여 userInfo 조회
        UserInfoResponse userInfoResponse = userClient.getUserInfo(token);

        if (userInfoResponse == null || !"SUCCESS".equals(userInfoResponse.getResultCode())) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        UserDTO user = userInfoResponse.getResult();

        return analysisRepository.findByUserId(user.getUserNo()).stream()
                .map(AnalysisHistory::from)
                .collect(Collectors.toList());
    }

    public AnalysisResponse findAnalysisById(long id, String token) {
        final Analysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 감정 분석 결과입니다."));

        DrinkResponse drinkResponse = drinkClient.getDrinkById(analysis.getDrinkId(), token);
        return AnalysisResponse.from(analysis, drinkResponse);

    }

    @Transactional
    public AnalysisResponse postAnalysis(final Long userId, final AnalysisRequest request, String token) {
        // token을 사용하여 userInfo 조회
        log.info("post token----- " + token);
        UserInfoResponse userInfoResponse = userClient.getUserInfo(token);

        if (userInfoResponse == null || !"SUCCESS".equals(userInfoResponse.getResultCode())) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

//        UserDTO user = userInfoResponse.getResult();

        try {
            final Analysis analysis = analyze(request, token);
            log.info("drink token-------- "+ token);
            Analysis persistedAnalysis = analysisRepository.save(analysis);

            DrinkResponse drinkResponse = drinkClient.getDrinkById(persistedAnalysis.getDrinkId(), token);

            return AnalysisResponse.from(persistedAnalysis, drinkResponse);
        } catch (final IOException e) {
            log.error("IOException during analysis: " + e.getMessage());
            throw new IllegalArgumentException("분석 요청 중 문제가 발생했습니다.");
        }
    }

    private Analysis analyze(AnalysisRequest request, String token) throws IOException {
        final Sentiment sentiment = analysisProvider.analyze(request);

        // token을 사용하여 userInfo 조회
        UserInfoResponse userInfoResponse = userClient.getUserInfo(token);

        if (userInfoResponse == null || !"SUCCESS".equals(userInfoResponse.getResultCode())) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        UserDTO user = userInfoResponse.getResult();

        // api요청 보내기
        String sentimentValue = sentiment.name();
        DrinkResponse drinkResponse = drinkClient.getRecommendedDrink(sentimentValue, token);

        return Analysis.builder()
                .sentiment(sentiment)
                .date(request.getDate())
                .userId(user.getUserNo())
                .textExpression(request.getTextExpression())
                .drinkId(drinkResponse.getId())
                .build();

    }

    // drinktionary로 이관
//    private Drink getRecommendedDrink(final Sentiment sentiment) {
//        final List<Drink> drinks = drinkRepository.findBySentiment(sentiment);
//        Collections.shuffle(drinks);
//
//        return drinks.get(0);
//    }


}
