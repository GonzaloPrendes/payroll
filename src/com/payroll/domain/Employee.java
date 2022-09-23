package com.payroll.domain;

import com.payroll.common.Money;
import com.payroll.infrastructure.EmployeeStream;
import com.payroll.infrastructure.OptionalUnion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Employee {
    private final String name;
    private final PaymentClassification payment;
    private final PaymentScheduler scheduler;
    private final OptionalUnion affiliation;

    public Employee(String name, PaymentClassification payment, PaymentScheduler scheduler) {
        this(name, payment, scheduler, OptionalUnion.empty());
    }

    public Employee(String name, PaymentClassification payment, PaymentScheduler scheduler, OptionalUnion affiliation) {
        this.name = name;
        this.payment = payment;
        this.scheduler = scheduler;
        this.affiliation = affiliation;
    }

    public Optional<Employee> isPayDay(LocalDate date) {
        return this.scheduler.isPayDay(date)
                ? Optional.of(this)
                : Optional.empty();
    }

    public PayCheck compensation(LocalDate date) {
        return new PayCheck(this, date, this.payment.estimateCompensation(date, this.scheduler), this.getDeductions(date));
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nClassification: %s", this.name, this.payment);
    }

    private Money getDeductions(LocalDate payDate) {
        return this.affiliation.deductions(payDate, this.scheduler);
    }

    static EmployeeStream stream(List<Employee> employees) {
        return new EmployeeStream(employees.stream());
    }
}
