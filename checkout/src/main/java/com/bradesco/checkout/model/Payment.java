package com.bradesco.checkout.model;

import com.bradesco.checkout.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment_tb")
public class Payment implements Serializable {
    private static final long serialVersionUID = -4412948369635862300L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal value;

    private long orderId;

    private LocalTime transaction_date;

    private PaymentStatus status;

    private String pixKey;

    private String bankName;

    private String bankCode;




}
