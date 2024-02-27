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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public void join(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void join(Admin admin) {
        adminRepository.save(admin);
    }

    @Transactional
    public void join(Seller seller) {
        sellerRepository.save(seller);
    }

    @Transactional
    public Member login(String username, String password) {

        List<Member> findMembers = memberRepository.findMemberByUsernameAndPassword(username, password);
        if (findMembers.isEmpty())
            throw new MemberNotFoundException("사용자를 찾을 수 없습니다");

        return findMembers.get(0);
    }

    @Transactional
    public void updateSellerBusiness(Long sellerId, String businessName, String businessInfo) {
        Seller findSeller = sellerRepository.findById(sellerId).orElseThrow();

        findSeller.updateBusiness(businessName, businessInfo);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String phoneNumber, String nickName) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        findCustomer.update(name, phoneNumber, nickName);
    }

    @Transactional
    public void updateAdmin(Long adminId, String name, String phoneNumber, String nickName) {
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        findAdmin.update(name, phoneNumber, nickName);
    }

    @Transactional
    public void updateSeller(Long sellerId, String name, String phoneNumber) {
        Seller findSeller = sellerRepository.findById(sellerId).orElseThrow();
        findSeller.update(name, phoneNumber);
    }

    @Transactional
    public void suspend(Long adminId, Long memberId, LocalDateTime date) {
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        Member findMember = memberRepository.findById(memberId);

        findMember.setSuspendDate(date);
    }
    
    // Admin이 Member의 일시중지를 취소
    @Transactional
    public void cancelSuspend(Long adminId, Long memberId) {
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow();
        Member findMember = memberRepository.findById(memberId);

        findMember.setSuspendDate(null);
    }
    
    // 일시 중지 시간이 지나면 취소
    @Transactional
    public void cancelSuspend(Long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember.getSuspendDate().isAfter(LocalDateTime.now())) {
            findMember.setSuspendDate(null);
        }
    }

    @Transactional
    public void withdraw(Long memberId) {
        Member findMember = memberRepository.findById(memberId);
        findMember.setWithdrawDate(LocalDateTime.now());
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
