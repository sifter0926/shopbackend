package org.pgm.shopserver.service;

import org.pgm.shopserver.dto.PurchasesDTO;
import org.pgm.shopserver.model.Purchase;
import org.pgm.shopserver.repository.projection.PurchaseItem;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(PurchasesDTO purchaseDTO);
    List<PurchaseItem> findPurchaseItemsOfUser(String username);
    List<Purchase> findAllPurchases();
    void deletePurchase(Long id);
}
