package com.stackoverflow.repositories;

import com.stackoverflow.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository  extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId(Long questionId);
}
