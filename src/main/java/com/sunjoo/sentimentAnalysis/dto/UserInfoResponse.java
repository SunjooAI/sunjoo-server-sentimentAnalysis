package com.sunjoo.sentimentAnalysis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserInfoResponse {
    private String resultCode;
    private UserDTO result;

}
