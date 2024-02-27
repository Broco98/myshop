package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Admin;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Member;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.AdminRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.SellerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@Transactional
class MemberServiceTest {

    private final MemberService memberService;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    @Test
    void 회원가입_소비자() {
        // given
        Customer customer = Customer.createCustomer("123", "123", "123", "123", "123");

        // when
        memberService.join(customer);

        // then
        Customer findCustomer = (Customer) memberService.login(customer.getUsername(), customer.getPassword());
        List<Customer> findCustomers = customerRepository.findAll();
        List<Admin> findAdmins = adminRepository.findAll();
        log.info("findCustomer={}", findCustomer);
        Assertions.assertThat(findCustomer).isEqualTo(customer);
        Assertions.assertThat(findCustomers.size()).isEqualTo(1);
        Assertions.assertThat(findAdmins.size()).isEqualTo(0);
    }

    @Test
    void 회원가입_관리자() {
        // given
        Admin admin = Admin.createAdmin("123", "123", "123", "123", "123");

        // when
        memberService.join(admin);

        // then
        Admin findAdmin = (Admin) memberService.login(admin.getUsername(), admin.getPassword());
        log.info("findAdmin={}", findAdmin);
        List<Admin> findAdmins = adminRepository.findAll();
        List<Customer> findCustomers = customerRepository.findAll();
        Assertions.assertThat(findAdmin).isEqualTo(admin);
        Assertions.assertThat(findCustomers.size()).isEqualTo(0);
        Assertions.assertThat(findAdmins.size()).isEqualTo(1);
    }

    @Test
    void 회원가입_판매자() {
        // given
        Seller seller = Seller.createSeller("123", "123", "123", "123");

        // when
        memberService.join(seller);

        // then
        Seller findSeller = (Seller) memberService.login(seller.getUsername(), seller.getPassword());
        log.info("findCustomer={}", findSeller);
        List<Customer> findCustomers = customerRepository.findAll();
        List<Seller> findSellers = sellerRepository.findAll();
        Assertions.assertThat(findSeller).isEqualTo(seller);
        Assertions.assertThat(findCustomers.size()).isEqualTo(0);
        Assertions.assertThat(findSellers.size()).isEqualTo(1);
    }

}