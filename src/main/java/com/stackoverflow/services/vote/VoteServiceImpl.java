package com.stackoverflow.services.vote;

import com.stackoverflow.dtos.QuestionVoteDto;
import com.stackoverflow.entities.Question;
import com.stackoverflow.entities.QuestionVote;
import com.stackoverflow.entities.User;
import com.stackoverflow.enums.VoteType;
import com.stackoverflow.repositories.QuestionRepository;
import com.stackoverflow.repositories.QuestionVoteRepository;
import com.stackoverflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionVoteRepository questionVoteRepository;


    @Override
    public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto) {
        Optional<User> optionalUser = userRepository.findById(questionVoteDto.getUserId());
        Optional<Question> optionalQuestion = questionRepository.findById(questionVoteDto.getQuestionId());

        if (optionalQuestion.isPresent() && optionalUser.isPresent()){
            QuestionVote questionVote = new QuestionVote();

            Question existingQuestion = optionalQuestion.get();

            questionVote.setVoteType(questionVoteDto.getVoteType());

            if(questionVote.getVoteType() == VoteType.UPVOTE){
                existingQuestion.setVoteCount(existingQuestion.getVoteCount() + 1);
            }else {
                existingQuestion.setVoteCount(existingQuestion.getVoteCount() - 1);
            }

            questionVote.setQuestion(optionalQuestion.get());
            questionVote.setUser(optionalUser.get());

            questionRepository.save(existingQuestion);

            QuestionVote votedQuestion = questionVoteRepository.save(questionVote);
            QuestionVoteDto questionVotedDto = new QuestionVoteDto();
            questionVotedDto.setId(votedQuestion.getId());
            return questionVotedDto;
        }
        return null;
    }
}
