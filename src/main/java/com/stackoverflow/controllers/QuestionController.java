package com.stackoverflow.controllers;

import com.stackoverflow.dtos.AllQuestionResponseDto;
import com.stackoverflow.dtos.QuestionDTO;
import com.stackoverflow.dtos.SingleQuestionDto;
import com.stackoverflow.entities.Question;
import com.stackoverflow.services.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDTO questionDto){
         QuestionDTO createdQuestionDto = questionService.addQuestion(questionDto);
         if (createdQuestionDto == null){
             return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
         }
         return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDto);
    }

    @GetMapping("/questions/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto = questionService.getAllQuestions(pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);
    }

    @GetMapping("/question/{userId}/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long userId , @PathVariable Long questionId){
        SingleQuestionDto singleQuestionDto = questionService.getQuestionById(userId, questionId);
        if (singleQuestionDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(singleQuestionDto);
    }

    @GetMapping("/questions/{userId}/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getQuestionsByUserId(@PathVariable Long userId, @PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto = questionService.getAllQuestionsByUserId(userId, pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);
    }

}
