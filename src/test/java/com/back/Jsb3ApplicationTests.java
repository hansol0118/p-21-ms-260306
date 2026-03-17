package com.back;

import com.back.domain.post.question.entity.Question;
import com.back.domain.post.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class Jsb3ApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
        Question q1 = new Question("제목1","내용1");
        questionRepository.save(q1);
        Question q2 = new Question("제목2","내용2");
        questionRepository.save(q2);

        List<Question> list = questionRepository.findAll();
        assertEquals(2, list.size());

        Question q = questionRepository.findById(1).get();
        assertEquals("제목1", q.getTitle());

    }
}
