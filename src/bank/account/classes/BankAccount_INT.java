package bank.account.classes;

public class BankAccount_INT extends BankAccount {

    private String origin;      // Kraj pochodzenia


    public BankAccount_INT(String id_lok, String name_lok, String balance, String origin_lok) {
        super(id_lok, name_lok, balance);

        origin = origin_lok;

    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Klient: " + getName() + " z " + getOrigin() + " o id: " + getID() + " posiada saldo na rachunku " + getBalance();
    }
}
