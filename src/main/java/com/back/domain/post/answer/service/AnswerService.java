package com.back.domain.post.answer.service;

import com.back.domain.post.answer.entity.Answer;
import com.back.domain.post.answer.repository.AnswerRepository;
import com.back.domain.post.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer writeAnswer(Question question, String content){
        Answer answer = new Answer(question,content);
        answerRepository.save(answer);
        return answer;
    }

}
