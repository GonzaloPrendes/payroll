package com.payroll.domain.classification;

import com.payroll.common.Money;
import com.payroll.domain.*;
import com.payroll.infrastructure.SaleReceiptStream;

import java.time.LocalDate;
import java.util.List;

public class CommissionedClassification implements PaymentClassification {

    private final List<SaleReceipt> sales;
    private final CommissionRate commissionRate;


    public CommissionedClassification(List<SaleReceipt> sales, CommissionRate commissionRate) {
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    @Override
    public Money estimateCompensation(LocalDate date, PaymentScheduler scheduler) {
        return this.commissionRate.getEarning(this.getSalesAmount(date, scheduler));
    }

    @Override
    public String toString() {
        return String.format("Commissioned");
    }

    private Money getSalesAmount(LocalDate payDate, PaymentScheduler scheduler) {
        return this.sales()
                .thoseInPayPeriod(payDate, scheduler)
                .sum();
    }

    private SaleReceiptStream sales() {
        return SaleReceipt.stream(this.sales);
    }
}
