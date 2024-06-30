package com.sunjoo.sentimentAnalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "result_content", columnDefinition = "TEXT")
    private String resultContent;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "text_expression")
    private String textExpression;

    @Column(name = "sentiment")
    @Enumerated(value = EnumType.STRING)
    private Sentiment sentiment;


}
