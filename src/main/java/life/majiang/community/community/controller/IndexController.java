package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private QuestionService questionServiceImpl;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize){

        PaginationDTO pagination = questionServiceImpl.list(pageNum,pageSize);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
