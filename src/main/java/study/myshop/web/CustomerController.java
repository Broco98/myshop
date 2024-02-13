package study.myshop.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Seller;
import study.myshop.service.CustomerService;
import study.myshop.web.dto.member.CustomerJoinForm;
import study.myshop.web.dto.member.SellerJoinForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/join")
    public String joinForm() {
        return "customer/join";
    }

    @PostMapping("/join")
    public String join(CustomerJoinForm form) {
        log.info("form={}", form);

        Customer customer = new Customer(
                form.getUsername(),
                form.getPassword(),
                form.getName(),
                form.getPhoneNumber(),
                form.getNickName());
        customerService.save(customer);

        return "redirect:/";
    }

}
