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
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        PaymentMethod paymentMethod = PaymentMethod.valueOf(method);
        PaymentStatus status = PaymentStatus.PENDING;

        if (paymentMethod == PaymentMethod.VOUCHER) {
            String voucherCode = paymentData.getOrDefault("voucherCode", "");
            status = isValidVoucher(voucherCode) ? PaymentStatus.SUCCESS : PaymentStatus.REJECTED;
        } else if (paymentMethod == PaymentMethod.BANK_TRANSFER) {
            if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")
                    || paymentData.get("bankName").isEmpty() || paymentData.get("referenceCode").isEmpty()) {
                status = PaymentStatus.REJECTED;
            }
        }

        Payment payment = new Payment(paymentMethod, paymentData);
        payment.setStatus(status);

        paymentRepository.save(payment);

        if (status == PaymentStatus.SUCCESS) {
            order.setStatus("SUCCESS");
        } else if (status == PaymentStatus.REJECTED) {
            order.setStatus("FAILED");
        }

        return payment;
    }


    @Override
    public Payment setStatus(Payment payment, PaymentStatus status) {
        payment.setStatus(status);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private boolean isValidVoucher(String voucherCode) {
        return voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")
                && voucherCode.replaceAll("[^0-9]", "").length() == 8;
    }
}
