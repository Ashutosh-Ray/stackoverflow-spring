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
    public String getResponseFromGenAI(@RequestBody GTPDto gptDto)  {
        String responseFromGenAI = "";
        try {
            responseFromGenAI = gptApiClient.getResponseFromGpt(gptDto.getQuery());
        }
        catch (IOException e) {
            System.out.println("Exception occured");
            return e.getMessage();
        }
        return responseFromGenAI;
    }
}
