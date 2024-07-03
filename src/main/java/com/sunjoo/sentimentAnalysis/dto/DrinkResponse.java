package com.sunjoo.sentimentAnalysis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DrinkResponse {
    private Long id;
    private String name;
    private double dosu;
    private int price;
    private int volume;
    private int sweetness;
    private String image;
    private String type;
}
