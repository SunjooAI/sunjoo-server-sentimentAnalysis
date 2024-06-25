package com.sunjoo.sentimentAnalysis.client;

import com.sunjoo.sentimentAnalysis.dto.DrinkResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "drinktionary-service", url = "http://sunjoo-server-drinktionary-drinktionary-1:8091")
public interface DrinkClient {

    @GetMapping("/drinks/{drinkId}")
    DrinkResponse getDrinkById(@PathVariable("drinkId") Long drinkId, @RequestHeader("Authorization") String token);
}
