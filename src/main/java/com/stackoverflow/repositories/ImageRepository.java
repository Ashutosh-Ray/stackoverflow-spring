package com.stackoverflow.repositories;

import com.stackoverflow.entities.Answer;
import com.stackoverflow.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByAnswer(Answer answer);
}
