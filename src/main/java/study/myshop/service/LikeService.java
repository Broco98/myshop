package study.myshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myshop.domain.item.Item;
import study.myshop.domain.like.LikeItem;
import study.myshop.domain.like.LikeSeller;
import study.myshop.domain.member.Customer;
import study.myshop.domain.member.Seller;
import study.myshop.repository.item.ItemRepository;
import study.myshop.repository.like.LikeItemRepository;
import study.myshop.repository.like.LikeSellerRepository;
import study.myshop.repository.member.CustomerRepository;
import study.myshop.repository.member.SellerRepository;

import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeItemRepository likeItemRepository;
    private final LikeSellerRepository likeSellerRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Transactional
    public void addLikeItem(Long customerId, Long itemId) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        Item findItem = itemRepository.findById(itemId);

        LikeItem likeItem = LikeItem.createLikeItem(findItem);
        findCustomer.addLikeItem(likeItem);
    }

    @Transactional
    public void removeLikeItem(Long customerId, Long likeItemId) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        LikeItem findLikeItem = likeItemRepository.findById(likeItemId).orElseThrow();
        findCustomer.removeLikeItem(findLikeItem);
    }

    @Transactional
    public void addLikeSeller(Long customerId, Long sellerId) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        Seller findSeller = sellerRepository.findById(sellerId).orElseThrow();

        LikeSeller likeSeller = LikeSeller.createLikeSeller(findSeller);
        findCustomer.addLikeSeller(likeSeller);
    }

    @Transactional
    public void removeLikeSeller(Long customerId, Long likeSellerId) {
        Customer findCustomer = customerRepository.findById(customerId).orElseThrow();
        LikeSeller findLikeseller = likeSellerRepository.findById(likeSellerId).orElseThrow();
        findCustomer.removeLikeSeller(findLikeseller);
    }

    public Set<LikeItem> findMyLikeItems(Long customerId) {
        // customer를 조회해서 list를 리턴해도 됨 -> 하지만, customer의 정보는 필요 없으므로 likeItemRepository에서 조회하도록 함
        return likeItemRepository.findByCustomerId(customerId);
    }

    public Set<LikeSeller> findMyLikeSellers(Long customerId) {
        return likeSellerRepository.findByCustomerId(customerId);
    }

}
