//package study.myshop.web;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import study.myshop.domain.member.Member;
//import study.myshop.service.MemberService;
//import study.myshop.web.dto.member.LoginMember;
//import study.myshop.service.exception.MemberNotFoundException;
//import study.myshop.web.dto.member.LoginForm;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @GetMapping("/login")
//    public String loginForm(
//            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) LoginMember loginMember
//    ) {
//
//        if (loginMember != null){
//            return "redirect:/";
//        }
//
//        return "login";
//    }
//
//    @GetMapping("/join")
//    public String joinForm(
//            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) LoginMember loginMember
//    ) {
//
//        if (loginMember != null) {
//            return "redirect:/";
//        }
//
//        return "join";
//    }
//
//    @PostMapping("/login")
//    public String login(LoginForm form, BindingResult result, HttpServletRequest request) {
//
//        if (result.hasErrors()) {
//            return "login";
//        }
//
//        try {
//            Member findMember = memberService.login(form.getUsername(), form.getPassword());
//            log.info("Login member={}", findMember); // Customer로그인 -> Customer가 반환됨
//            HttpSession session = request.getSession();
//            LoginMember loginMember = new LoginMember(findMember.getId(), findMember.getClass().getSimpleName());
//
//            log.info("LoginMember id={}, dtype={}", findMember.getId(), findMember.getClass().getSimpleName());
//
//            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//        } catch(MemberNotFoundException e) {
//            result.reject("loginFail", "회원이 존재하지 않습니다.");
//            return "login";
//        }
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
//
//}
