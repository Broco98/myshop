package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Address;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Member;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.*;
import study.myshop.exception.MemberNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

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

    public void addAddress(Long memberId, Address address) {
        Member findMember = memberRepository.findById(memberId);
        findMember.addAddress(address);
    }

    public void removeAddress(Long memberId, Address address) {
        Member findMember = memberRepository.findById(memberId);
        findMember.removeAddress(address);
    }

    // TODO -> Member를 찾고 -> Member의 Address객체를 반환해도 될 것 같은대 -> 그럼 2번 sql이 작성되는거 아닌가?
//    public List<Address> findMyAddresses(Long memberId) {
//        return addressRepository.findByMemberId(memberId);
//    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
