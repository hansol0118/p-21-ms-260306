package com.back.domain.post.question.service;

import com.back.DataNotFoundException;
import com.back.domain.post.answer.entity.Answer;
import com.back.domain.post.question.entity.Question;
import com.back.domain.post.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    public Question findById(int id){
        Optional<Question> question = questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Question writeQuestion(String title, String content){
        Question question = new Question(title,content);
        return questionRepository.save(question);
    }
}
