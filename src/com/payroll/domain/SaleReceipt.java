package com.payroll.domain;

import com.payroll.common.Money;
import com.payroll.infrastructure.SaleReceiptStream;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SaleReceipt {

    private final LocalDate date;
    private final Money amount;

    private SaleReceipt(LocalDate date, Money amount) {
        this.date = date;
        this.amount = amount;
    }

    public static SaleReceipt of(LocalDate date, Money amount) {
        return new SaleReceipt(date, amount);
    }

    public static SaleReceiptStream stream(List<SaleReceipt> sales) {
        return new SaleReceiptStream(sales.stream());
    }

    public Optional<SaleReceipt> isInPayPeriod(LocalDate payDate, PaymentScheduler scheduler) {
        return scheduler.isDateInBetween(this.date, payDate)
                ? Optional.of(this)
                : Optional.empty();
    }

    public Money getAmount() {
        return this.amount;
    }
}
