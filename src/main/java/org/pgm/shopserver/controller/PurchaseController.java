package org.pgm.shopserver.controller;

import lombok.RequiredArgsConstructor;
import org.pgm.shopserver.dto.PurchasesDTO;
import org.pgm.shopserver.security.UserPrinciple;
import org.pgm.shopserver.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Object> savePurchase(@RequestBody PurchasesDTO purchasesDTO){
        return new ResponseEntity<>(purchaseService.savePurchase(purchasesDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Object> getAllPurchasesOfUser(@AuthenticationPrincipal
                                                            UserPrinciple userPrinciple){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+userPrinciple.getUser());
        return ResponseEntity.ok(purchaseService.findPurchaseItemsOfUser(userPrinciple.getUsername()));
    }
}
