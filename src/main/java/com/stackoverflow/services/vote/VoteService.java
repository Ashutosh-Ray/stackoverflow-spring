package com.stackoverflow.services.vote;

import com.stackoverflow.dtos.QuestionVoteDto;

public interface VoteService {
    public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto);
}
