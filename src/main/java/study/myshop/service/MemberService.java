package study.myshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.member.Member;
import study.myshop.domain.member.Seller;
import study.myshop.repository.member.AdminRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.MemberRepository;
import study.myshop.service.exception.MemberNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member login(String username, String password) {

        List<Member> result = memberRepository.findByUserNameAndPassword(username, password);
        if (result.isEmpty())
            throw new MemberNotFoundException("사용자를 찾을 수 없습니다");

        return result.get(0);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
