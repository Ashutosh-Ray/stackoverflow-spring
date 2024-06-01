package com.stackoverflow.repositories;

import com.stackoverflow.entities.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
