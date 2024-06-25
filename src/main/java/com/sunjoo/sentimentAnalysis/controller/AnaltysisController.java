package com.sunjoo.sentimentAnalysis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class AnaltysisController {

    @GetMapping("/getList")
    public void getList(@RequestHeader("userNo") String userNo) {
        log.info("analys 서비스 호출");
        log.info("login user no : " + userNo);
    }
}
