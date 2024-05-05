package com.bradesco.checkout.controller;

import com.bradesco.checkout.dto.PaymentRequestDTO;
import com.bradesco.checkout.dto.PaymentResponseDTO;
import com.bradesco.checkout.service.PaymentService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Base64;

@RestController
@RequestMapping("${request.mapping.payment}")
@Tag(name = "Payment-API")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(
            responses = {
                    @ApiResponse(description = "Process payment via pix.", responseCode = "201")
            },
            summary = "",
            tags = {}
    )
    public ResponseEntity<PaymentResponseDTO> receivePayment(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO){

        PaymentResponseDTO response;
        try {
          response = paymentService.receivePayment(paymentRequestDTO);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/generate-qr-code", consumes = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            responses = {
                    @ApiResponse(description = "Generate an image of a valid qr code pix.", responseCode = "200")
            },
            summary = "",
            tags = {}
    )
    public ResponseEntity<String> generateQRCode(@RequestParam String pixKey, @RequestParam BigDecimal value, @RequestParam String bank){

        String base64Image;
        try {

           byte[] qrCode = paymentService.generateQrCode(pixKey, value, bank);
           base64Image = Base64.getEncoder().encodeToString(qrCode);

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body("{\"image\": \"" + base64Image + "\"}");
    }
}
