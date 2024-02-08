package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Admin;
import study.myshop.repository.member.AdminRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

}
