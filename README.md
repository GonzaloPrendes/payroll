# Payroll

It is an experimental sample app mixing OOP and Functional paradigms. The idea was to experiment with creating something that could be maintainable, scalable, and clean without using verbose code with domain classes closely related to business requirements.

I took the exercise from the book "Agile Software Development, Principles, Patterns, and Practices" by Robert C. Martin

# Simple Requirements

- Some employees work by the hour. They are paid an hourly rate. They submit daily time cards that record the date and the number of hours worked. If they work more than 8 hours per day, they are paid 1.5 times their normal rate for those extra hours. They are paid every Friday.

- Some employees are paid a flat salary. They are paid on the last working day of the month.

- Some of the salaried employees are also paid a commission based on their sales. They submit sales receipts that record the date and the amount of the sale. They are paid every other Friday.

- Some employees belong to the union. Their dues must be deducted from their pay.

- The payroll application will run once each working day and pay the appropriate employees on that day. The system will be told to what date the employees are to be paid, so it will calculate payments from the last time the employee was paid up to the specified date.

# Further Requirements
- Employees can select their method of payment. They may have their paychecks mailed to the postal address of their choice; they may have their paychecks held for pickup by the Paymaster; or they can request that their paychecks be directly deposited into the bank account of their choice.

- The union may assess service charges against individual union members from time to time. These service charges are submitted by the union on a weekly basis and must be deducted from the appropriate employee’s next pay amount.

