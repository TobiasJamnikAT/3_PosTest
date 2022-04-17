package Bsp_403_Konto_Guat;

public class BankAccount {
    private String IBAN;
    private double balance;

    public BankAccount(String IBAN, double balance) {
        this.IBAN = IBAN;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getIBAN() {
        return IBAN;
    }

    public boolean transfer(double value){
        if(balance + value > 0){
            balance = balance + value;
            return true;
        } else{
            return false;
        }
    }
}
