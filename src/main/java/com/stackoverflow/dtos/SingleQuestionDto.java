package com.stackoverflow.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuestionDto {
    private QuestionDTO questionDTO;
    private List<AnswerDto> answerDtoList;
}
