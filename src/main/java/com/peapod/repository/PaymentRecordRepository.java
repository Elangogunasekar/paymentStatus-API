package com.peapod.repository;


import com.peapod.model.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
    PaymentRecord findByTokenIdAndId(String tokenId, String userId);
}
