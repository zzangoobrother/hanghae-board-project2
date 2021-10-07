package com.sparta.hanghaeboardproject2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String writer;
    private String contents;
    private Long boardId;
    private Long answerId;
}
