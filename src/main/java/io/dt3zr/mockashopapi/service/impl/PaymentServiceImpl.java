package io.dt3zr.mockashopapi.service.impl;

import io.dt3zr.mockashopapi.service.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PaymentServiceImpl implements PaymentService {
    private AtomicLong counter = new AtomicLong();

    @Override
    public String send(BigDecimal amount) {
        String runningNumber = String.valueOf(counter.getAndAdd(1));
        String padding = "00000000";
        StringBuilder builder = new StringBuilder();
        builder.append("TX");
        builder.append(padding.substring(0, padding.length() - runningNumber.length()));
        builder.append(runningNumber);
        return builder.toString();
    }
}
