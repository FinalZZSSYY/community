package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.pojo.User;
import life.majiang.community.community.service.QuestionService;
import life.majiang.community.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    private UserService userServiceImpl;
    @Resource
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getName());
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO pagination = questionService.list(user.getId(), pageNum, pageSize);
            model.addAttribute("paginationByUser", pagination);
        } else if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "最新回复");
            PaginationDTO pagination = questionService.list(user.getId(), pageNum, pageSize);
            model.addAttribute("paginationByUser", pagination);
        }

        return "profile";
    }
}
