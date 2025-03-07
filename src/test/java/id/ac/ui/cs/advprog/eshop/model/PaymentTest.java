package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(PaymentMethod.VOUCHER, paymentData);

        assertNotNull(payment.getId());
        assertEquals(PaymentMethod.VOUCHER, payment.getMethod());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testSetValidStatus() {
        Payment payment = new Payment(PaymentMethod.BANK_TRANSFER, new HashMap<>());
        payment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testSetInvalidStatusThrowsException() {
        Payment payment = new Payment(PaymentMethod.BANK_TRANSFER, new HashMap<>());

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(PaymentStatus.valueOf("INVALID_STATUS"));
        });
    }
}
