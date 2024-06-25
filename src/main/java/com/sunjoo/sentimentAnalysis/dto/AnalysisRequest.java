package com.sunjoo.sentimentAnalysis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnalysisRequest {

    private String textExpression;
    private LocalDateTime date = LocalDateTime.now();

    public boolean isNotExistExpressions() {
        return textExpression == null;
    }
}
