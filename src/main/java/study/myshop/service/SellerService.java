package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.SellerRepository;
import study.myshop.service.exception.MemberNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller login(String username, String password) {

        List<Seller> result = sellerRepository.findByUserNameAndPassword(username, password);
        if (result.isEmpty())
            throw new MemberNotFoundException("사용자를 찾을 수 없습니다");

        return result.get(0);
    }

}
