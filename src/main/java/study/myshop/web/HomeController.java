package study.myshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String home() {
        return "home";
    }

//    @GetMapping("join")
//    public String join(){
//        return "join";
//    }
//
//    @GetMapping("login")
//    public String login(){
//        return "login";
//    }
}
