package com.back.domain.post.answer.controller;

import com.back.domain.post.answer.entity.Answer;
import com.back.domain.post.answer.repository.AnswerRepository;
import com.back.domain.post.answer.service.AnswerService;
import com.back.domain.post.question.entity.Question;
import com.back.domain.post.question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    public record AnswerForm(
            @NotEmpty(message = "댓글 내용은 필수항목입니다.")
            @Size(min = 2, max = 200, message = "댓글 내용은 2글자 이상 200글자 이하로 입력해주세요.")
            String content) {
    }

    @PostMapping("/{questionId}")
    public String write(@PathVariable("questionId") Integer questionId, Model model, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = questionService.findById(questionId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.writeAnswer(question,answerForm.content);

        return String.format("redirect:/question/%s", questionId);
    }

}
