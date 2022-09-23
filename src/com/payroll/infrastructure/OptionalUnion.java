package com.payroll.infrastructure;

import com.payroll.common.Money;
import com.payroll.domain.PaymentScheduler;
import com.payroll.domain.Union;

import java.time.LocalDate;
import java.util.Optional;

public class OptionalUnion {

    private final Optional<Union> content;

    public OptionalUnion(Optional<Union> content) {
        this.content = content;
    }

    public static OptionalUnion empty() {
        return new OptionalUnion(Optional.empty());
    }

    public static OptionalUnion of(Union union) {
        return new OptionalUnion(Optional.of(union));
    }

    public Money deductions(LocalDate payDate, PaymentScheduler scheduler) {
        return this.content.map(union -> union.getDeductions(payDate, scheduler)).orElse(Money.ZERO);
    }
}
