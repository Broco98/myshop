package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Member;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.AdminRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.MemberRepository;
import study.myshop.repository.member.SellerRepository;
import study.myshop.service.exception.MemberNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    public void join(Customer customer) {
        customerRepository.save(customer);
    }

    public void join(Admin admin) {
        adminRepository.save(admin);
    }

    public void join(Seller seller) {
        sellerRepository.save(seller);
    }

    public Member login(String username, String password) {

        List<Member> findMembers = memberRepository.findByUsernameAndPassword(username, password);
        if (findMembers.isEmpty())
            throw new MemberNotFoundException("사용자를 찾을 수 없습니다");

        return findMembers.get(0);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
