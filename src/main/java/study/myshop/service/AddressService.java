package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.Address;
import study.myshop.domain.member.Customer;
import study.myshop.repository.AddressRepository;
import study.myshop.repository.member.CustomerRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long addAddress(Long customerId, String address) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        Address newAddress = Address.createAddress(findCustomer, address);

        addressRepository.save(newAddress);
        return newAddress.getId();
    }

    @Transactional
    public void removeAddress(Long customerId, Long addressId) {
        
        // Cascade를 사용해도 어차피 쿼리가 2번 나가는건 같으니까 나눠서 쿼리 진행
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        Address findAddress = addressRepository.findById(addressId).orElseThrow();

        checkCustomerAddress(findCustomer, findAddress);

        findCustomer.getAddresses().remove(findAddress);
    }

    @Transactional
    public void updateAddress(Long customerId, Long addressId, String address) {
        Customer findCustomer = customerRepository.findCustomerByIdWithAddresses(customerId);
        Address findAddress = addressRepository.findById(addressId).orElseThrow();

        checkCustomerAddress(findCustomer, findAddress);

        for (Address a : findCustomer.getAddresses()) {
            if (a.equals(findAddress)) a.updateAddress(address);
        }
    }

    private void checkCustomerAddress(Customer customer, Address address) {
        if (!customer.getAddresses().contains(address)) {
            throw new RuntimeException("해당 사용자의 주소가 아닙니다");
        }
    }


    public List<String> findAddresses(Long customerId) {
        Customer findCustomer = customerRepository.findCustomerByIdWithAddresses(customerId);

        return findCustomer.getAddresses().stream()
                .map(Address::getAddress)
                .toList();
    }

}
