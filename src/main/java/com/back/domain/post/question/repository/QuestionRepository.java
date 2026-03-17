package com.back.domain.post.question.repository;

import com.back.domain.post.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findByTitle(String title);
}
