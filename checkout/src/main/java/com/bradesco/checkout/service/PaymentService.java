package com.bradesco.checkout.service;

import com.bradesco.checkout.dto.PaymentRequestDTO;
import com.bradesco.checkout.dto.PaymentResponseDTO;
import com.google.zxing.WriterException;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentResponseDTO receivePayment(PaymentRequestDTO paymentRequestDTO) throws Exception;
    byte[] generateQrCode (String pixKey, BigDecimal value, String bank) throws WriterException;
}
