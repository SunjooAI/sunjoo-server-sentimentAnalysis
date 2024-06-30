package com.sunjoo.sentimentAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.sunjoo.sentimentAnalysis.client")
public class SentimentAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentimentAnalysisApplication.class, args);
	}

}
