package com.csi.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csi.utils.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
