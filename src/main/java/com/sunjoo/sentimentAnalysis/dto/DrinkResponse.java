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
    private long capacity;
    private double sugar;
    private String image;
    private String type;
}
