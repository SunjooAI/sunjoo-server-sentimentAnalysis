package com.sunjoo.sentimentAnalysis.client;

import com.sunjoo.sentimentAnalysis.dto.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://13.124.194.48:8090")
public interface UserClient {

    @GetMapping("/auth/userinfo")
    UserInfoResponse getUserInfo(@RequestHeader("Authorization") String token);
}
