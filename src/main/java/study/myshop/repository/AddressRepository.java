package study.myshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.myshop.domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByMemberId(Long memberId);

    @Query("select a from Address a where a.id=:id and a.member.id =:memberId")
    Address findByIdAndMemberId(Long id, Long memberId);

}
