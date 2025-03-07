package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
    }


    @Override
    public Payment setStatus(Payment payment, PaymentStatus status) {
    }

    @Override
    public Payment getPayment(String paymentId) {
    }

    @Override
    public List<Payment> getAllPayments() {
    }

    private boolean isValidVoucher(String voucherCode) {

    }
}
