package com.bradesco.checkout.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO implements Serializable {
    private static final long serialVersionUID = -6530322009713064068L;

    @JsonProperty("status_transaction")
    private String status_transaction;

    @JsonProperty("date")
    private String date_time_transaction;

    @JsonProperty("qrCode-image")
    private String qrCode;
}
