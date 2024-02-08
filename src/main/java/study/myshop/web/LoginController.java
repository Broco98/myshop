package study.myshop.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.myshop.domain.member.Member;
import study.myshop.service.MemberService;
import study.myshop.web.dto.member.LoginForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("login")
    public String login(LoginForm form) {
        Member findMember = memberService.login(form.getUsername(), form.getPassword());

        log.info("member={}", findMember);

        return "login";
    }

}
