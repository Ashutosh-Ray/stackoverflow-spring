package com.stackoverflow.controllers;

import com.stackoverflow.dtos.QuestionVoteDto;
import com.stackoverflow.services.vote.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping("/vote")
    public ResponseEntity<?> addVoteToQuestion(@RequestBody QuestionVoteDto questionVoteDto){
        QuestionVoteDto questionVotedDto = voteService.addVoteToQuestion(questionVoteDto);
        if(questionVotedDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(questionVotedDto);
        }

    }


}
