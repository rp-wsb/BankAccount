import bank.account.classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Driver {

    public static final long START_COVID = LocalDate.of(2020, Month.APRIL, 1).toEpochDay();
    public static final long STOP_COVID = LocalDate.of(2022, Month.JANUARY, 1).toEpochDay();


    public static void main(String[] args) {
        ArrayList<BankAccount> accountList = new ArrayList<>();
        try{

            readBankAccounts(accountList);
        }catch (Exception e){
            System.out.println("Exception read");
        }
        RunMenu(accountList);

    }

    private static void RunMenu(ArrayList<BankAccount> accountList) {
        JDialog menuDialog = new JDialog();

        JButton b1 = new JButton("List Bank Accounts");
        JButton b2 = new JButton("Add New Bank Account");
        JButton b3 = new JButton("Close Bank Accounts");
        JButton b4 = new JButton("Deposit cash");
        JButton b5 = new JButton("Withdraw cash");
        JButton b6 = new JButton("Exit & Save");

        b1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        menuDialog.setVisible(false);
                        JOptionPane.showMessageDialog(
                                null,
                                listAccounts(accountList),
                                "Bank Accounts",
                                JOptionPane.PLAIN_MESSAGE
                        );
                        menuDialog.setVisible(true);
                    }
                }
        );
        b2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        menuDialog.setVisible(false);
                        createdBankAccount(accountList);
                        menuDialog.setVisible(true);
                    }
                }
        );
        b3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        menuDialog.setVisible(false);
                        cloesingAccount(accountList);
                        menuDialog.setVisible(true);
                    }
                }
        );
        b4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        menuDialog.setVisible(false);
                        String input = JOptionPane.showInputDialog("Put ID or REGON ");
                        boolean flag = false;
                        for (BankAccount bankAccount : accountList) {
                            if(bankAccount.getID().equals(input) ||
                                    bankAccount.getRegon().equals(input)){
                                input = JOptionPane.showInputDialog("How many deposit cash? ");
                                bankAccount.deposit(input);
                                flag = true;
                            }
                        }
                        if(flag){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "not found ID or REGON",
                                    "Bank Accounts",
                                    JOptionPane.PLAIN_MESSAGE
                            );
                        }
                        menuDialog.setVisible(true);
                    }
                }
        );
        b5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        menuDialog.setVisible(false);
                        String input = JOptionPane.showInputDialog("Put ID or REGON ");
                        boolean flag = true;
                        for (BankAccount bankAccount : accountList) {
                            if(bankAccount.getID().equals(input) ||
                                    bankAccount.getRegon().equals(input)){
                                input = JOptionPane.showInputDialog("How many cash withdraw ");
                                bankAccount.withdraw(input);
                                flag = false;
                            }
                        }
                        if(flag){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "not found",
                                    "Bank Accounts",
                                    JOptionPane.PLAIN_MESSAGE
                            );
                        }
                        menuDialog.setVisible(true);
                    }
                }
        );
        b6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        saveBankAccounts(accountList);
                        System.exit(0);

                    }
                }
        );

        menuDialog.add(b1);
        menuDialog.add(b2);
        menuDialog.add(b3);
        menuDialog.add(b4);
        menuDialog.add(b5);
        menuDialog.add(b6);

        menuDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        menuDialog.setLocationRelativeTo(null);
        menuDialog.setLayout(new FlowLayout());
        menuDialog.setSize(300, 300);
        menuDialog.setVisible(true);
    }

    private static void cloesingAccount(ArrayList<BankAccount> accountList) {
        String input = JOptionPane.showInputDialog("Put ID or REGON ");
        boolean flag = true;
        try{
            for (BankAccount bankAccount : accountList) {
                if(bankAccount.getID().equals(input) ||
                        bankAccount.getRegon().equals(input)){
                    bankAccount.close(accountList);
                    flag = false;
                }
            }
        }catch (Exception e){
            //ConcurrentModificationException
        }
        if(flag){
            JOptionPane.showMessageDialog(
                    null,
                    "not found",
                    "Bank Accounts",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
    }

    private static boolean run(ArrayList<BankAccount> accountList) {
        int inputInt = JOptionPane.showConfirmDialog(
                null,
                "Are you want created new Bank Account? ",
                "Created Bank Account",
                JOptionPane.YES_NO_OPTION);
        if (inputInt == JOptionPane.YES_OPTION) {
            createdBankAccount(accountList);
        } else {
            inputInt = JOptionPane.showConfirmDialog(
                    null,
                    "Exit and print Bank Account? ",
                    "Created Bank Account",
                    JOptionPane.YES_NO_OPTION);
            if (inputInt == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                        null,
                        listAccounts(accountList),
                        "Bank Accounts",
                        JOptionPane.PLAIN_MESSAGE
                );
                return true;
            }
        }
        return false;
    }

    private static StringBuilder listAccounts(ArrayList<BankAccount> accountList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BankAccount bankAccount : accountList) {
            stringBuilder.append("<{  ").append(bankAccount).append("  }>\n");
        }
        return stringBuilder;
    }

    private static void readBankAccounts(ArrayList<BankAccount> accountList) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                "files.txt"
                        )
                )
        );


        try {

            while (true) {
                accountList.add((BankAccount) in.readObject());
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (EOFException e) {
            // koniec
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null) in.close();
        }
    }

    private static void saveBankAccounts(ArrayList<BankAccount> accountList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    "files.txt"
                            )
                    )
            );

            for (BankAccount bankAccount : accountList) {
                out.writeObject(bankAccount);
            }

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createdBankAccount(ArrayList<BankAccount> accountList) {
        LocalDate date = LocalDate.now();
        ArrayList<String> inputData = new ArrayList<>();
        int inputInt = JOptionPane.showConfirmDialog(
                null,
                "Are you a company? ",
                "Created Bank Account",
                JOptionPane.YES_NO_OPTION);
        if (inputInt == JOptionPane.YES_OPTION) {
            inputData.add(JOptionPane.showInputDialog("What is the REGON of the company? "));
            inputData.add(JOptionPane.showInputDialog("What is name of the company? "));
            inputData.add(JOptionPane.showInputDialog("What is the initial balance of the company? "));
            if (date.toEpochDay() < START_COVID || date.toEpochDay() > STOP_COVID) {
                accountList
                        .add(
                                new BankAccount_FIRMA(
                                        inputData.get(0),
                                        inputData.get(1),
                                        Double.parseDouble(inputData.get(2))
                                )
                        );
            } else {
                accountList
                        .add(
                                new BankAccount_FIRMA_COVID19(
                                        inputData.get(0),
                                        inputData.get(1),
                                        Double.parseDouble(inputData.get(2)),     //poprawic konstruktor
                                        date
                                )
                        );
            }
        } else {
            inputInt = JOptionPane.showConfirmDialog(
                    null,
                    "Is your nationality Polish? ",
                    "Created Bank Account",
                    JOptionPane.YES_NO_OPTION);

            inputData.add(JOptionPane.showInputDialog("What is your ID? "));
            inputData.add(JOptionPane.showInputDialog("What is your name? "));
            inputData.add(JOptionPane.showInputDialog("What is the initial balance? "));
            if (inputInt == JOptionPane.YES_OPTION) {
                if (date.toEpochDay() < START_COVID || date.toEpochDay() > STOP_COVID) {
                    accountList
                            .add(
                                    new BankAccount(
                                            inputData.get(0),
                                            inputData.get(1),
                                            inputData.get(2)
                                    )
                            );
                } else {
                    accountList
                            .add(
                                    new BankAccount_COVID19(
                                            inputData.get(0),
                                            inputData.get(1),
                                            inputData.get(2)
                                    )
                            );
                }
            } else {
                // Get the origin
                inputData.add(JOptionPane.showInputDialog("What is your nationality? "));
                accountList
                        .add(
                                new BankAccount_INT(
                                        inputData.get(0),
                                        inputData.get(1),
                                        inputData.get(2),
                                        inputData.get(3)
                                )
                        );
            }
        }
    }

}