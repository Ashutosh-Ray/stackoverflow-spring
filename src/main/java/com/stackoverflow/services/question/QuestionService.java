package com.stackoverflow.services.question;

import com.stackoverflow.dtos.AllQuestionResponseDto;
import com.stackoverflow.dtos.QuestionDTO;
import com.stackoverflow.dtos.SingleQuestionDto;

public interface QuestionService {
    QuestionDTO addQuestion(QuestionDTO questionDto);

    AllQuestionResponseDto getAllQuestions(int pageNumber);

    SingleQuestionDto getQuestionById(Long userId, Long questionId);

    AllQuestionResponseDto getAllQuestionsByUserId(Long userId, int pageNumber);
}
