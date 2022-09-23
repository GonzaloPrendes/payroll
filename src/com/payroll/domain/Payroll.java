package com.payroll.domain;

import com.payroll.infrastructure.EmployeeStream;

import java.time.LocalDate;
import java.util.List;

public class Payroll {

    private final List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public void run(LocalDate today) {
        this.employees()
                .withPayDay(today)
                .pay(today)
                .deliverPayCheck();
    }

    private EmployeeStream employees() {
        return Employee.stream(this.employees);
    }
}
