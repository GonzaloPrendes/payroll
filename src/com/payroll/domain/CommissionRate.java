package com.payroll.domain;

import com.payroll.common.Money;

public class CommissionRate {
    private final Money baseSalary;
    private final double rate;

    private CommissionRate(Money baseSalary, double rate) {
        this.baseSalary = baseSalary;
        this.rate = rate;
    }

    public static CommissionRate of(Money baseSalary, double rate) {
        return new CommissionRate(baseSalary, rate);
    }

    public Money getEarning(Money salesAmount) {
        return this.baseSalary.plus(salesAmount.scale(rate));
    }
}
