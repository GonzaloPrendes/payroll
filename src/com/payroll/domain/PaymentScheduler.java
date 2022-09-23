package com.payroll.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface PaymentScheduler {
    boolean isDateInBetween(LocalDate date, LocalDate payDate);

    boolean isPayDay(LocalDate date);
}
