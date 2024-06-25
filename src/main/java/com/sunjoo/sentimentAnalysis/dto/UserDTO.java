package com.sunjoo.sentimentAnalysis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDTO {
    private Long userNo;
    private String id;
    private String name;
    private String type;
}
