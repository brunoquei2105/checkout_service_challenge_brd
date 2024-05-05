package com.bradesco.checkout.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDING,
    APPROVED,
    IN_PROGRESS,
    NOT_APPROVED;
}
