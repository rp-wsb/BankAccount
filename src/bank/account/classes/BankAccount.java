package bank.account.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
public class BankAccount implements Serializable {
    private double balance;      // Account balance
    private String id;           // Identyfikator klienta
    private String name;         // Nazwa klienta

    public BankAccount() {
        balance = 0.0;
        id = " ";
        name = " ";
    }

    /**
     * This constructor sets the starting balance
     * to the value passed as an argument.
     *
     * @param startBalance The starting balance.
     */

    public BankAccount(double startBalance, String id_lok, String name_lok) {
        balance = startBalance;
        id = id_lok;
        name = name_lok;
    }

    /**
     * This constructor sets the starting balance
     * to the value in the String argument.
     *  The starting balance, as a String.
     */

    public BankAccount(String id_lok, String name_lok,String balance) {
        this.balance = Double.parseDouble(balance);
        id = id_lok;
        name = name_lok;

    }

    /**
     * The deposit method makes a deposit into
     * the account.
     *
     * @param amount The amount to add to the
     *               balance field.
     */

    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * The deposit method makes a deposit into
     * the account.
     *
     * @param str The amount to add to the
     *            balance field, as a String.
     */

    public void deposit(String str) {
        balance += Double.parseDouble(str);
    }

    /**
     * The withdraw method withdraws an amount
     * from the account.
     *
     * @param amount The amount to subtract from
     *               the balance field.
     */

    public void withdraw(double amount) {
        balance -= amount;
    }

    /**
     * The withdraw method withdraws an amount
     * from the account.
     *
     * @param str The amount to subtract from
     *            the balance field, as a String.
     */

    public void withdraw(String str) {
        balance -= Double.parseDouble(str);
    }

    public void close(List<BankAccount> a) {
        withdraw(balance);
        a.removeIf(this::equals);

    }

    /**
     * The getBalance method returns the
     * account balance.
     *
     * @return The value in the balance field.
     */

    public double getBalance() {
        return balance;
    }

    /**
     * The setBalance method sets the account balance.
     *
     * @param b The value to store in the balance field.
     */

    public void setBalance(double b) {
        balance = b;
    }

    /**
     * The setBalance method sets the account balance.
     *
     * @param str The value, as a String, to store in
     *            the balance field.
     */

    public void setBalance(String str) {
        balance = Double.parseDouble(str);
    }

    public String getName() {
        return name;
    }

    public void setName(String name_lok) {
        name = name_lok;
    }

    public String getRegon() {
        return "";
    }

    public String getID() {
        return id;
    }

    public void setID(String id_lok) {
        id = id_lok;
    }

    public String getOrigin() {
        return "POLAND";
    }

    protected void setOrigin(String orgin) {

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;

        if (Double.compare(that.getBalance(), getBalance()) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getBalance());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Klient: " + getName() + " o id: " + getID() + " posiada saldo: " + getBalance();
    }


}

