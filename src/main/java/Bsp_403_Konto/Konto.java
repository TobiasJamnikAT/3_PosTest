package Bsp_403_Konto;

import java.util.Random;

public class Konto {
    private double balance;
    private String iban;

    public double getBalance() {
        return balance;
    }

    public String getIban() {
        return iban;
    }

    public Konto(double value, String iban) {
        if(balance < 50){
            balance = 50;
        } else{
            this.balance = value;
        }
        this.iban = iban;
    }

    public void addMoney(double value){
        balance = balance + value;
    }

    public boolean retrieveMoney(double value){
        if(balance - value >= 0){
            balance = balance - value;
            return true;
        } else{
            return false;
        }
    }

}
