package com.stackoverflow.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String body;
    private Date createdDate;
    private List<String> tags;
    private Long userId;
    private String username;
    private Integer voteCount = 0;
    private int voted;
}
