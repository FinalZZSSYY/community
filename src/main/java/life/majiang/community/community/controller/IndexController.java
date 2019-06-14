package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.pojo.User;
import life.majiang.community.community.service.QuestionService;
import life.majiang.community.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private UserService userServiceImpl;

    @Resource
    private QuestionService questionServiceImpl;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie :cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userServiceImpl.findByToken(token);
                    System.out.println(token);
                    if (user != null){
                        System.out.println(user.getName());
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }

        }

        List<QuestionDTO> questionList = questionServiceImpl.list();
        model.addAttribute("questions", questionList);
        return "index";
    }
}
