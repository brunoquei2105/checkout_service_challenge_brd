package com.bradesco.checkout.qr_code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public class QRCodePixGenerator {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public static byte[] generateQRCode(String pixData) throws WriterException {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(pixData, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int argb = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                outputStream.write((argb >> 16) & 0xFF);
                outputStream.write((argb >> 8) & 0xFF);
                outputStream.write(argb & 0xFF);
            }
        }

        return outputStream.toByteArray();
    }
}
