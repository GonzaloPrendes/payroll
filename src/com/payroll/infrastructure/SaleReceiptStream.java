package com.payroll.infrastructure;

import com.payroll.common.Money;
import com.payroll.domain.PaymentScheduler;
import com.payroll.domain.SaleReceipt;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class SaleReceiptStream implements ForwardingStream<SaleReceipt> {

    private final Stream<SaleReceipt> stream;

    public SaleReceiptStream(Stream<SaleReceipt> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<SaleReceipt> getStream() {
        return this.stream;
    }

    public MoneyStream thoseInPayPeriod(LocalDate payDate, PaymentScheduler scheduler) {
         return Money.stream(
                 this.stream.map(sale -> sale.isInPayPeriod(payDate, scheduler))
                         .filter(Optional::isPresent)
                         .map(Optional::get)
                         .map(SaleReceipt::getAmount)
         );

    }
}
