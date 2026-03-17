package com.back.domain.post.question.controller;


import com.back.domain.post.answer.controller.AnswerController;
import com.back.domain.post.question.entity.Question;
import com.back.domain.post.question.repository.QuestionRepository;
import com.back.domain.post.question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/list")
    public String list(Model model) {

        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    record QuestionForm(
            @NotEmpty(message = "제목은 필수항목입니다.")
            @Size(min = 2, max = 10, message = "제목은 2글자 이상 10글자 이하로 입력해주세요.")
            String title,

            @NotEmpty(message = "내용은 필수항목입니다.")
            @Size(min = 2, max = 200, message = "내용은 2글자 이상 200글자 이하로 입력해주세요.")
            String content) {
    }

    @GetMapping("/write")
    public String writeQuestion(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/write")
    public String doWriteQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        questionService.writeQuestion(questionForm.title, questionForm.content);
        return "redirect:/question/list";
    }

    @GetMapping("/{id}")
    public String list(@PathVariable("id") Integer id, Model model, AnswerController.AnswerForm answerForm) {

        Question question = questionService.findById(id);
        model.addAttribute("question", question);

        return "question_detail";
    }
}
