// File: src/main/java/com/example/walletapi/controller/PaymentController.java
package com.peapod.controller;

import com.peapod.exception.TokenNotFoundException;
import com.peapod.model.Constants;
import com.peapod.response.PaymentTokenResponse;
import com.peapod.service.PaymentService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getPaymentStatus")
    public ResponseEntity<PaymentTokenResponse> getTokenStatus(@RequestParam @NotBlank String tokenId, @RequestParam @NotBlank String appName,
                                                               @RequestParam @NotBlank String UserId, @RequestParam @NotBlank String brandName) throws TokenNotFoundException {
        try {
            PaymentTokenResponse paymentTokenResponse = paymentService.getPaymentStatus(tokenId, appName,UserId,brandName);
            return ResponseEntity.ok().body(paymentTokenResponse);
        }  catch (IllegalArgumentException e) {
            PaymentTokenResponse errorResponse = new PaymentTokenResponse();
            errorResponse.setStatus(Constants.INVALID);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        } catch (TokenNotFoundException e) {
            PaymentTokenResponse errorResponse = new PaymentTokenResponse();
            errorResponse.setStatus("ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
