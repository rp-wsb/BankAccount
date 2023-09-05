## PROJECT JUSTIFICATION:
Let's imagine that it is the end of March 2020 and we are working for a financial institution as IT specialists.
We have ordered that from April 1, 2020, IT systems will prevent cash withdrawals exceeding PLN 1,000.
PLN per day.
At the same time, these restrictions do not apply to bank accounts for foreigners (international law - principle
reciprocity, etc.).
If we are dealing with a company account during the pandemic, the first deposit is a non-refundable loan
government loan in the amount of PLN 5,000 but it is not possible to close the account - up to 2 years after the end of the pandemic.

The program should create an account based on the class:
+ BankAccount - if we have a date before April 1, 2020 or after January 1, 2022 (before the start of the pandemic or
after the end of the pandemic)
+ BankAccount_COVID19 - if we have a date between April 1, 2020 and before January 1, 2022,
+ BankAccount_INT - if we are dealing with an international client,
+ BankAccount_COVID19_company - for corporate clients if we have a date between April 1, 2020 and
before January 1, 2022,
+ BankAccount_firma - for corporate clients if the date is before April 1, 2020 or after January 1
2022 (before the start of the pandemic or after the end of the pandemic).

### FUNCTIONAL SPECIFICATIONS:
Project Assumptions:
1. Create:
   - 1 superclass : BankAccount
   - 4 subclasses:
     - BankAccount_INT, BankAccount_firma, BankAccount_COVID19, BankAccount_COVID19_firma
   - Attribute fields:
     - BankAccount: double balance, String id, String name (account balance, customer ID, customer name)
     - BankAccount_INT: additional field String origin (country of origin)
     - BankAccount_Firma: additional String field REGON (ID value then empty)
   - Methods available within the BankAccount class:
     - deposit() method - Depositing funds into the account,
     - Withdraw() method - Withdrawing funds from the account (with limit below)
     - Close() method - Closing the account (running the withdraw method (withdrawing funds from
account) and deleting the object).
     - toString() method – to print information about the client (values of attribute fields along with consistent
text)

2. Create a driver - the main program.
As part of the main program, Bank employees should be able to create an account
for the client (PL or INT or Company) and the client should be asked whether he wants to deposit or withdraw funds from
account (withdraw() and deposit() methods)
3. Data should be retrieved using dialog boxes and, before creating objects, be
held in arrays: array[] or ArrayList&lt;&gt;*
4. Saving objects (clients) to text files (one file for all clients).
5. Loading data from text files into arrays: array[] or ArrayList&lt;&gt;*.
7. Displaying data in dialog boxes.
