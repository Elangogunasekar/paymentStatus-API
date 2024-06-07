package com.peapod.service;


import com.peapod.exception.TokenNotFoundException;
import com.peapod.model.PaymentRecord;
import com.peapod.repository.PaymentRecordRepository;
import com.peapod.response.PaymentTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    public PaymentTokenResponse getPaymentStatus(String tokenId, String appName, String UserId, String brandName) throws TokenNotFoundException {
        PaymentTokenResponse paymentTokenResponse = new PaymentTokenResponse();
        PaymentRecord paymentRecord = paymentRecordRepository.findByTokenIdAndId(tokenId,UserId);
        if (paymentRecord != null) {
            String status = paymentRecord.getStatus();
            paymentTokenResponse.setStatus(status);
            return paymentTokenResponse;
        } else {
            throw new TokenNotFoundException("Token not found for tokenId: " + tokenId);
        }
    }
}
