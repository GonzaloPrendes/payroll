package com.payroll.domain;

import com.payroll.common.Money;

import java.time.LocalDate;

public interface PaymentClassification {

    Money estimateCompensation(LocalDate date, PaymentScheduler scheduler);
}
