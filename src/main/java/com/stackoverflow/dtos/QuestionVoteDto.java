package com.stackoverflow.dtos;

import com.stackoverflow.enums.VoteType;
import lombok.Data;

@Data
public class QuestionVoteDto {
    private Long id;

    private VoteType voteType;

    private Long userId;

    private Long questionId;
}
