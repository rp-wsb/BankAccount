package bank.account.classes;

import java.time.LocalDate;
import java.util.List;

public class BankAccount_FIRMA_COVID19 extends BankAccount_FIRMA {

    LocalDate accountOpeningDate;

    public BankAccount_FIRMA_COVID19(String regon, String name_lok, double startBalance, LocalDate date) {
        super(regon, name_lok, (startBalance+5000.0));
        accountOpeningDate = date;
    }

    @Override
    public void close(List<BankAccount> a) {
        if(LocalDate.now().toEpochDay() >= accountOpeningDate.plusYears(2L).toEpochDay()) {
            super.close(a);
        }
    }
}
