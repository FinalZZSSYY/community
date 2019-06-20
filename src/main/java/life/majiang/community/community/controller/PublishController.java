package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.pojo.Question;
import life.majiang.community.community.pojo.User;
import life.majiang.community.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Resource
    private QuestionService questionServiceImpl;


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,
                       Model model){
        QuestionDTO questionDTO = questionServiceImpl.getById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(Model model, HttpServletRequest request,
                            @RequestParam(value = "title",required = false)String title,
                            @RequestParam(value = "description",required = false)String description,
                            @RequestParam(value = "tag",required = false)String tag,
                            @RequestParam(value = "id",required = false)Integer id){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if(title == null || title.equals("")){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description == null || description.equals("")){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionServiceImpl.createOrUpdate(question);
        return "redirect:/";//重定向
    }
}
