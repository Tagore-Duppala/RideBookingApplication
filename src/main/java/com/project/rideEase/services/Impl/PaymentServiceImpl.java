package com.project.rideEase.services.Impl;

import com.project.rideEase.entities.Payment;
import com.project.rideEase.entities.Ride;
import com.project.rideEase.entities.enums.PaymentStatus;
import com.project.rideEase.repositories.PaymentRepository;
import com.project.rideEase.services.PaymentService;
import com.project.rideEase.stratagies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride);
        paymentStrategyManager.paymentMethod(ride).processPayment(payment);
        log.info("Payment processed!");
    }

    @Override
    public Payment createPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .paymentMethod(ride.getPaymentMethod())
                .build();
        log.info("Payment created!");
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
        log.info("Payment status updated!");
    }
}
