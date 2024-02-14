//package study.myshop.web;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import study.myshop.domain.member.Seller;
//import study.myshop.service.ItemService;
//import study.myshop.service.SellerService;
//import study.myshop.web.dto.member.SellerJoinForm;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("seller")
//public class SellerController {
//
//    private final SellerService sellerService;
//
//    @GetMapping("/join")
//    public String joinForm() {
//        return "seller/join";
//    }
//
//    @PostMapping("/join")
//    public String join(SellerJoinForm form) {
//        log.info("form={}", form);
//
//        Seller seller = new Seller(form.getUsername(), form.getPassword(), form.getName(), form.getPhoneNumber());
//        sellerService.save(seller);
//
//        return "home";
//    }
//
//}
