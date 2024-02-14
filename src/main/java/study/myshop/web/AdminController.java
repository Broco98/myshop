//package study.myshop.web;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import study.myshop.domain.member.Admin;
//import study.myshop.domain.member.Customer;
//import study.myshop.service.AdminService;
//import study.myshop.service.CustomerService;
//import study.myshop.web.dto.member.AdminJoinForm;
//import study.myshop.web.dto.member.CustomerJoinForm;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final AdminService adminService;
//
//    @GetMapping("/join")
//    public String joinForm() {
//        return "join";
//    }
//
//    @PostMapping("/join")
//    public String join(AdminJoinForm form) {
//        log.info("form={}", form);
//
//        Admin admin = new Admin(
//                form.getUsername(),
//                form.getPassword(),
//                form.getName(),
//                form.getPhoneNumber(),
//                form.getNickName());
//        adminService.save(admin);
//
//        return "redirect:/";
//    }
//
//}
