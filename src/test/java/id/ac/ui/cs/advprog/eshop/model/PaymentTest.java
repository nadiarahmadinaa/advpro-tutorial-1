package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("VOUCHER", paymentData);

        assertNotNull(payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testSetValidStatus() {
        Payment payment = new Payment("BANK_TRANSFER", new HashMap<>());
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetInvalidStatusThrowsException() {
        Payment payment = new Payment("BANK_TRANSFER", new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATUS"));
    }
}
