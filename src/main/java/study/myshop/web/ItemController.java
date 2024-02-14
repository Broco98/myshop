package study.myshop.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.SellerRepository;
import study.myshop.service.ItemService;
import study.myshop.service.SellerService;
import study.myshop.web.dto.member.LoginMember;
import study.myshop.web.dto.item.ItemAddForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final SellerRepository sellerRepository;

    @GetMapping("/add")
    public String itemAddForm(
            @SessionAttribute(SessionConst.LOGIN_MEMBER) LoginMember loginMember
            ) {

        if (loginMember != null && loginMember.getDtype().equals("Seller")){
            return "item/add";
        }

        log.info("잘못된 사용자");
        return "redirect:/";
    }

    @PostMapping("/add")
    public String itemAdd(
            @SessionAttribute(SessionConst.LOGIN_MEMBER) LoginMember loginMember,
            ItemAddForm form) {

        if (loginMember == null || !loginMember.getDtype().equals("Seller")){
            return "redirect:/";
        }
        
        // TODO seller에 salesItems 리스트를 만들어서 등록하기
        
        Seller seller = sellerRepository.findById(loginMember.getId());
        itemService.add(seller, form.getName(), form.getSalesQuantityGram(), form.getSalesQuantityNum(), form.getOriginalPrice(), form.getStock(), form.getDescription());

        return "home"; // TODO -> 등록 item으로 이동하도록 수정
    }

}
