package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Payment payment = new Payment(PaymentMethod.BANK_TRANSFER, new HashMap<>());
        paymentRepository.save(payment);

        assertNotNull(paymentRepository.findById(payment.getId()));
    }

    @Test
    void testFindPaymentById() {
        Payment payment = new Payment(PaymentMethod.VOUCHER, new HashMap<>());
        paymentRepository.save(payment);

        assertEquals(payment, paymentRepository.findById(payment.getId()));
    }

    @Test
    void testFindAllPayments() {
        Payment payment1 = new Payment(PaymentMethod.BANK_TRANSFER, new HashMap<>());
        Payment payment2 = new Payment(PaymentMethod.VOUCHER, new HashMap<>());
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        assertEquals(2, paymentRepository.findAll().size());
    }
}
