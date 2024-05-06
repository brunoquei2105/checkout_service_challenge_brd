package com.bradesco.checkout.service.impl;


import com.bradesco.checkout.dto.PaymentRequestDTO;
import com.bradesco.checkout.dto.PaymentResponseDTO;
import com.bradesco.checkout.enums.PaymentStatus;
import com.bradesco.checkout.model.Payment;
import com.bradesco.checkout.qr_code.QRCodePixGenerator;
import com.bradesco.checkout.repository.PaymentRepository;
import com.bradesco.checkout.service.PaymentService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Random;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
       this.paymentRepository = paymentRepository;

   }

    @Override
    public PaymentResponseDTO receivePayment(PaymentRequestDTO paymentRequestDTO) throws WriterException {

        Payment payment = Payment.builder()
                .value(new BigDecimal(String.valueOf(paymentRequestDTO.getValue())))
                .transaction_date(LocalTime.now())
                .orderId(new Random().nextLong())
                .status(PaymentStatus.PENDING)
                .build();
        //Publica os dados do pagamento na fila para ser consumida pelo sistema do banco
        //rabbitMQSender.publish(payment);
        paymentRepository.save(payment);

        //gera o qr code para ser retornado no response
        byte[] qrCode = generateQrCode(paymentRequestDTO.getPixKey(), paymentRequestDTO.getValue(), paymentRequestDTO.getBankName());
        String base64Image = Base64.getEncoder().encodeToString(qrCode);

        return PaymentResponseDTO.builder()
                .status_transaction(PaymentStatus.IN_PROGRESS.toString())
                .date_time_transaction(payment.getTransaction_date().toString())
                .qrCode(base64Image)
                .build();

        //TODO: Apos o sistema do banco central processar o pagamento a API de Integracao deve consumir de uma fila
        // o status do Payment e atualizar no DB.
    }

    @Override
    public byte[] generateQrCode(String pixKey, BigDecimal value, String bank) throws WriterException {
        String pixData = generateDataPixPayment(pixKey,value, bank);
        return QRCodePixGenerator.generateQRCode(pixData);
    }


    /*
        The prefix "000000000101" ensures that the QR CODE
        be recognized as a valid PIX payment by scanners and payment apps.
     */
    private String generateDataPixPayment(String pixKey, BigDecimal value, String bank){
        return "000000000101" + pixKey + bank + value.toString().replace(".", ",");

    }
}
