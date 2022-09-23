package com.payroll.domain.classification;

import com.payroll.common.Money;
import com.payroll.domain.PaymentClassification;
import com.payroll.domain.PaymentScheduler;

import java.time.LocalDate;

public class SalariedClassification implements PaymentClassification {

    private final Money flatSalary;

    public SalariedClassification(Money flatSalary) {
        this.flatSalary = flatSalary;
    }

    @Override
    public Money estimateCompensation(LocalDate date, PaymentScheduler scheduler) {
        return this.flatSalary;
    }

    @Override
    public String toString() {
        return String.format("Salaried");
    }
}
