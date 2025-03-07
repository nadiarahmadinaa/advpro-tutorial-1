package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {
    private PaymentServiceImpl paymentService;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl(new PaymentRepository());

        ArrayList<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("P001", "Sample Product", 10000));

        mockOrder = new Order("12345", sampleProducts, 1708560000L, "Customer");
    }

    @Test
    void testAddPaymentVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = paymentService.addPayment(mockOrder, "VOUCHER", paymentData);

        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
        assertEquals("SUCCESS", mockOrder.getStatus());
    }

    @Test
    void testAddPaymentVoucherRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDVOUCHER");

        Payment payment = paymentService.addPayment(mockOrder, "VOUCHER", paymentData);

        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
        assertEquals("FAILED", mockOrder.getStatus());
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "123456");

        Payment payment = paymentService.addPayment(mockOrder, "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.PENDING, payment.getStatus());
    }

    @Test
    void testAddPaymentBankTransferRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "123456");

        Payment payment = paymentService.addPayment(mockOrder, "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment(PaymentMethod.VOUCHER, new HashMap<>());
        paymentService.setStatus(payment, PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testGetPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = paymentService.addPayment(mockOrder, "VOUCHER", paymentData);

        assertEquals(payment, paymentService.getPayment(payment.getId()));
    }

    @Test
    void testGetAllPayments() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        paymentService.addPayment(mockOrder, "VOUCHER", paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank ABC");
        paymentData2.put("referenceCode", "123456");
        paymentService.addPayment(mockOrder, "BANK_TRANSFER", paymentData2);

        assertEquals(2, paymentService.getAllPayments().size());
    }
}
