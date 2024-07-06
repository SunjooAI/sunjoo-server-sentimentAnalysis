package com.sunjoo.sentimentAnalysis.client;

import com.sunjoo.sentimentAnalysis.dto.DrinkResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "drinktionary-service", url = "http://13.124.194.48:8091")
public interface DrinkClient {

    @GetMapping("/drinks/{drinkId}")
    DrinkResponse getDrinkById(@PathVariable("drinkId") Long drinkId, @RequestHeader("Authorization") String token);

    @GetMapping("/drinks/recommend")
    DrinkResponse getRecommendedDrink(@RequestParam("sentiment") String sentiment, @RequestHeader("Authorization") String token);
}
