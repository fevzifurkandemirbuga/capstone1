# Capstone 1: Accounting Ledger Application

A simple CLI ledger to record deposits and payments, persist them to a CSV file, and view built-in reports
(month-to-date, previous month, year-to-date, previous year), plus vendor and custom searches.

---

## Features
- Home Screen
```
********  Home ********
  D) Add Deposit
  P) Make Payment(Debit)
  L) Ledger
  X) Exit

```
  

- Ledger Menu
  
```
****  Ledger ****
A) All
D) Deposits
P) Payments
R) Reports
H) Home 

```
  

- Reports Menu
```
***** Reports *****
1) Month To Date
2) Previous Month
3) Year To Date
4) Previous Year
5) Search by vendor
6) Custom Search
0) Back

```

---

  ### Home Screen Menu
  - Add Deposit `addTransaction(true);`
  - Make Payment `addTransaction(false);`
  - Display Ledger `displayLedger()`

  ### Ledger Screen Menu
  - Display All Entries `displayAll()`
  - Deposits `displayDeposits()`
  - Payments `displayPayments()`
  - Reports `displayReports()`
  
  ### Reports Screen Menu
  
  - 1)Month To Date `monthToDateReport()`
  - 2)Previous Month `previousMonthReport()`
  - 3)Year To Date `yearToDateReport`
  - 4)Previous Year `previousYearReport()`
  - 5)Search by vendor `searchByVendor()`
  - 6)Custom Search `customSearch()`


## Data Model

`Transaction` fields:

- `LocalDate date`
- `LocalTime time`
- `String description`
- `String vendor`
- `double amount` 

## CSV Format

- Pipe-delimited: `date|time|description|vendor|amount`
- **Header line is expected** (the app skips the first line when reading)

Example:

date|time|description|vendor|amount <br>
2025-10-15|09:32:10|Salary|ACME Corp|2500.00 <br>
2025-10-16|14:05:44|Groceries|Fresh Market|-82.47