package com.bradesco.checkout.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDTO implements Serializable {
    private static final long serialVersionUID = -8966467086517094635L;

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("date_of_transaction")
    @NotBlank
    private String transaction_date;

    @NotBlank
    @JsonProperty("pix-key")
    private String pixKey;

    @JsonProperty("bankName")
    @NotBlank
    private String bankName;

    @JsonProperty("bankCode")
    private long bankCode;

}
