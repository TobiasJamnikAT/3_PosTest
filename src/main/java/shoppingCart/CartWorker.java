package shoppingCart;

import shoppingCart.beans.Product;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class  CartWorker implements Callable<Double> {
    private int noOperations = 10;
    private int name;
    private ShoppingCart cart;
    private List<Product> products;

    public CartWorker(int name, ShoppingCart cart, List<Product> products) {
        this.name = name;
        this.cart = cart;
        this.products = products;
    }

    @Override
    public Double call() throws Exception {
        Random randy = new Random();
        for(int i = 0; i < noOperations; i++){
            System.out.println(name + " starts");
            synchronized (cart){
                //0 - 99; 0 - 18 = weglegen; 20 - 99 = drauflegen
                int operation = randy.nextInt(100);
                if(operation >= 0 && operation <= 18){
                    cart.removeProduct(products.get(randy.nextInt(products.size() + 1)), Integer.toString(name));
                } else{
                    cart.addProduct(products.get(randy.nextInt(products.size() + 1)), Integer.toString(name));
                }
            }

            cart.notify();
        }

        return calcReturn();
    }

    public double calcReturn(){
        double sum = 0;
        for (int i = 0; i < products.size(); i++){
            sum = sum + products.get(i).getSum();
        }

        return sum;
    }
}
