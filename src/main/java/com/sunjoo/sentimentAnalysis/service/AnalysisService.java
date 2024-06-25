package com.sunjoo.sentimentAnalysis.service;

import com.sunjoo.sentimentAnalysis.client.DrinkClient;
import com.sunjoo.sentimentAnalysis.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnalysisService {

    private final DrinkClient drinkClient;
    private final UserClient userClient;


}
