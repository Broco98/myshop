package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.SellerRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}
