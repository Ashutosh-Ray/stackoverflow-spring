package com.stackoverflow.services.genai;

import com.stackoverflow.dtos.AnswerDto;
import com.stackoverflow.dtos.QuestionDTO;
import com.stackoverflow.services.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class GenerateResponseFromGPT {

    @Autowired
    GPTApiClient gptApiClient;
    @Autowired
    AnswerService answerService;
    public void saveResponseFromBot(QuestionDTO questionDTO) {
        answerService.postAnswer(generateResponse(questionDTO));
    }
    private AnswerDto generateResponse(QuestionDTO questionDTO) {
        String question = questionDTO.getBody();
        String responseFromGPT = "";
        try {
            responseFromGPT = gptApiClient.getResponseFromGpt(question);
        }
        catch (IOException e) {
            System.out.println("IOException thrown "+e.getMessage());
        }
        return createAnswerDto(responseFromGPT, questionDTO);
    }
    private AnswerDto createAnswerDto(String answer, QuestionDTO questionDTO) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setBody(answer);
        answerDto.setCreatedDate(new Date());
        answerDto.setUsername("StackExchangeBot");
        answerDto.setQuestionId(questionDTO.getId());
        answerDto.setUserId(3L);
        return answerDto;
    }
}
