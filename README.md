# Week 8 - S8 Practice Problems

Inheritance, method overriding, and runtime polymorphism — every problem
uses a base type, overridden calculation methods, and a polymorphic array
processed in one uniform loop (no type checks at processing time).

## Problems

1. `PaymentFeeCalculator` — CARD 2% / WALLET 1% / BANKTRANSFER 0% fee
2. `LibraryDueDateCalculator` — BOOK 14d / DVD 7d / MAGAZINE 3d from a fixed date
3. `DeliveryFeeCalculator` — per-type fee rules; INTERNATIONAL adds a customs fee
4. `ExamQuestionGrader` — MCQ/TF exact match; ESSAY keyword scoring (50%/75%)
5. `TransportFareCalculator` — BUS (capped $10) / TRAIN / METRO (peak factor)

## Run

Each program reads from stdin, competitive-style:

```bash
javac *.java
printf "3\nCARD 1000\nWALLET 500\nBANKTRANSFER 2000\n" | java PaymentFeeCalculator
```

## Note on Problem 3

The PDF's printed business rules and its expected output disagree:
`EXPRESS 5 20` under the printed rules is 24.00, but the expected output is
29.00. The rates here are set to match the expected output (Express $2.00/kg,
International $2.50/kg). If the grader intends the printed rates instead,
change those two constants.

`QUIZ.md` has the multiple-choice answers and short concept-question answers.
