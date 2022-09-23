package com.payroll.domain;

import com.payroll.common.Money;
import java.time.LocalDate;

public class PayCheck {

    private final Employee employee;
    private final LocalDate payDate;
    private final Money grossAmount;
    private final Money deductions;

    public PayCheck(Employee employee, LocalDate payDate, Money grossAmount, Money deductions) {
        this.employee = employee;
        this.payDate = payDate;
        this.grossAmount = grossAmount;
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        return String.format(
                "========Paycheck======\nDate: %s\n%s\nGross Amount: %s\nDeductions: %s\nNet Amount: %s\n======================",
                this.payDate, this.employee, this.grossAmount, this.deductions, this.grossAmount.substract(this.deductions));
    }
}
