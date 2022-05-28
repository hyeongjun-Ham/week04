package com.sparta.assignment031.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String checkPassword;
}
