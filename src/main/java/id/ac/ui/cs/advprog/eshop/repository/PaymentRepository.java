package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {
    private final Map<String, Payment> payments = new HashMap<>();

    public Payment save(Payment payment) {
    }

    public Payment findById(String paymentId) {
    }

    public List<Payment> findAll() {
    }
}
