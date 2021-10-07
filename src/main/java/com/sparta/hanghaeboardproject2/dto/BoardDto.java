package com.sparta.hanghaeboardproject2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String writer;
    private String contents;
}
