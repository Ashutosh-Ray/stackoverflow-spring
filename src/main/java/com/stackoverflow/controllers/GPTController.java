package com.stackoverflow.controllers;

import com.stackoverflow.dtos.GTPDto;
import com.stackoverflow.services.genai.GPTApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class GPTController {
    @Autowired
    GPTApiClient gptApiClient;
    @PostMapping("/getResponseFromGenAI")
    public ResponseEntity<String> getResponseFromGenAI(@RequestBody GTPDto gptDto)  {
        String responseFromGenAI = "";
        try {
            System.out.println("Query "+gptDto.getQuery());
            responseFromGenAI = gptApiClient.getResponseFromGpt(gptDto.getQuery());
        }
        catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseFromGenAI, HttpStatus.OK);
    }
}
