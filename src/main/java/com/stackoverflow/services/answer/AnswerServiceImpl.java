package com.stackoverflow.services.answer;

import com.stackoverflow.dtos.AnswerDto;
import com.stackoverflow.entities.Answer;
import com.stackoverflow.entities.Question;
import com.stackoverflow.entities.User;
import com.stackoverflow.repositories.AnswerRepository;
import com.stackoverflow.repositories.QuestionRepository;
import com.stackoverflow.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public AnswerDto postAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (optionalUser.isPresent() && optionalQuestion.isPresent()){
            Answer answer = new Answer();
            answer.setBody(answerDto.getBody());
            answer.setCreatedDate(new Date());
            answer.setQuestion(optionalQuestion.get());
            answer.setUser(optionalUser.get());

            Answer createdAnswer = answerRepository.save(answer);

            AnswerDto createdAnswerDto = new AnswerDto();
            createdAnswerDto.setId(createdAnswer.getId());

            return  createdAnswerDto;
        }
        return null;
    }
}
