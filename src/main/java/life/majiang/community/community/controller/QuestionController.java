package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionServiceImpl;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        //累加阅读数
        questionServiceImpl.insView(id);
        QuestionDTO questionDTO = questionServiceImpl.getById(id);

        model.addAttribute("question", questionDTO);
        return "question";
    }
}
