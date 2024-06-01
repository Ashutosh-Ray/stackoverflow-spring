package com.stackoverflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stackoverflow.dtos.AnswerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "body", length = 512)
    private String body;

    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;

    public AnswerDto getAnswerDto() {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(id);
        answerDto.setBody(body);
        answerDto.setUserId(user.getId());
        answerDto.setQuestionId(question.getId());
        answerDto.setUsername(user.getName());
        answerDto.setCreatedDate(createdDate);
    return answerDto;
    }
}
