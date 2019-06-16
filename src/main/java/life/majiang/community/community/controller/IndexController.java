package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.pojo.User;
import life.majiang.community.community.service.QuestionService;
import life.majiang.community.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    private UserService userServiceImpl;

    @Resource
    private QuestionService questionServiceImpl;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize){
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
        PaginationDTO pagination = questionServiceImpl.list(pageNum,pageSize);


        model.addAttribute("pagination", pagination);
        return "index";
    }
}
