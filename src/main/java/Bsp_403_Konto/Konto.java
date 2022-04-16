package Bsp_403_Konto;

import java.util.Random;

public class Konto {
    private double balance;
    private String iban;

    public Konto(double balance, String iban) {
        this.balance = balance;
        this.iban = iban;
    }

    private void addMoney(int value){
        balance = balance + value;
    }

    private boolean retrieveMoney(int value){
        if(balance - value >= 0){
            balance = balance - value;
            return true;
        } else{
            return false;
        }
    }

}
