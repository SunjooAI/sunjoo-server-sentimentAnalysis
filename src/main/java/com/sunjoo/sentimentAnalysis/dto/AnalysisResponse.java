package com.sunjoo.sentimentAnalysis.dto;

import com.sunjoo.sentimentAnalysis.client.DrinkClient;
import com.sunjoo.sentimentAnalysis.entity.Analysis;
import com.sunjoo.sentimentAnalysis.entity.Sentiment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class AnalysisResponse {

    private final Long id;
    private final String textExpression;
    private final LocalDateTime date;
    private final Sentiment sentiment;
    private final Long drinkId;
    private final String name;
    private final double dosu;
    private final int price;
    private final int volume;
    private final int sweetness;
    private final String drinkImageUrl;
    private final String type;

    public static AnalysisResponse from(final Analysis analysis, final DrinkResponse drinkResponse) {

        return AnalysisResponse.builder()
                .id(analysis.getResultId())
                .textExpression(analysis.getTextExpression())
                .date(analysis.getDate())
                .sentiment(analysis.getSentiment())
                .drinkId(analysis.getDrinkId())
                .name(drinkResponse.getName())
                .dosu(drinkResponse.getDosu())
                .price(drinkResponse.getPrice())
                .volume(drinkResponse.getVolume())
                .sweetness(drinkResponse.getSweetness())
                .drinkImageUrl(drinkResponse.getImage())
                .type(drinkResponse.getType())
                .build();
    }


}
