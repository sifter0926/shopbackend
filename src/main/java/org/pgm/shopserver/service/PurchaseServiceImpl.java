package org.pgm.shopserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.shopserver.dto.PurchasesDTO;
import org.pgm.shopserver.model.Product;
import org.pgm.shopserver.model.Purchase;
import org.pgm.shopserver.model.User;
import org.pgm.shopserver.repository.ProductRepository;
import org.pgm.shopserver.repository.PurchaseRepositroy;
import org.pgm.shopserver.repository.UserRepository;
import org.pgm.shopserver.repository.projection.PurchaseItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepositroy purchaseRepositroy;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Purchase savePurchase(PurchasesDTO purchaseDTO) {
        Purchase purchase = Purchase.builder()
                .quantity(purchaseDTO.getQuantity())
                .build();

        User user=userRepository.findByUsername(purchaseDTO.getUsername());
        Product product=productRepository.findById(purchaseDTO.getProductId()).orElseThrow();

        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setPurchaseTime(LocalDateTime.now());

        Purchase savedPurchase=purchaseRepositroy.save(purchase);
        return savedPurchase;
    }

    @Override
    public List<PurchaseItem> findPurchaseItemsOfUser(String username) {
        User user=userRepository.findByUsername(username);
        List<PurchaseItem> purchaseItems=purchaseRepositroy.findAllPurchasesOfUser(username);
        purchaseItems.forEach(purchaseItem->{
            log.info(purchaseItem.getQuantity());
            log.info(purchaseItem.getPurchaseTime());
        });
        return purchaseRepositroy.findAllPurchasesOfUser(username);
    }

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepositroy.findAll();
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepositroy.deleteById(id);
    }
}
