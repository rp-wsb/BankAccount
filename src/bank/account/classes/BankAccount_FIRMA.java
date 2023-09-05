package bank.account.classes;

public class BankAccount_FIRMA extends BankAccount {
    private String regon;

    public BankAccount_FIRMA(String regon, String name_lok, double startBalance) {
        super(startBalance, " ", name_lok);
        this.regon = regon;
    }
    @Override
    public String getRegon() {
        return regon;
    }

    @Override
    public String getID() {   // W firmie ID to regon
        return this.regon;
    }

    @Override
    public void setID(String id_lok) {

    }

    @Override
    public String toString() {
        return "Firma: " + getName() + " o nr REGON: " + getRegon() + " posiada saldo " + getBalance();
    }
}
