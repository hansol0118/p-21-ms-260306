package com.back.global.initData;

import com.back.domain.post.answer.entity.Answer;
import com.back.domain.post.answer.service.AnswerService;
import com.back.domain.post.question.entity.Question;
import com.back.domain.post.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            work1();
        };
    }

    @Transactional
    public void work1(){
        if(questionRepository.count() > 0) {
            return;
        }
        Question question1 = new Question("제목1", "내용1");
        Question question2 = new Question("제목2", "내용2");
        Question question3 = new Question("제목3", "내용3");
        Question question4 = new Question("제목4", "내용4");

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);

        answerService.writeAnswer(question1,"댓글 1-1");
        answerService.writeAnswer(question1,"댓글 1-2");
        answerService.writeAnswer(question2,"댓글 2-1");
        answerService.writeAnswer(question2,"댓글 2-2");
        answerService.writeAnswer(question3,"댓글 3-1");
        answerService.writeAnswer(question3,"댓글 3-2");
        answerService.writeAnswer(question4,"댓글 4-1");

    }
}
