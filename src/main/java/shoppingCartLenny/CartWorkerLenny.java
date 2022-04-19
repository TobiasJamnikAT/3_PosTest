package shoppingCartLenny;

import java.util.Random;
import java.util.concurrent.Callable;

public class CartWorkerLenny implements Callable<Double> {
    private int number;
    private ProductLenny product;
    private CartLenny shoppingCart;

    public CartWorkerLenny(int number, ProductLenny product, CartLenny shoppingCart) {
        this.number = number;
        this.product = product;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public Double call() throws Exception {
        System.out.println(number + " is starting...");

        Random random = new Random();
        double sum = 0;

        for (int i = 0; i < 10; i++) {
            double randomNumber = random.nextDouble();
            synchronized (shoppingCart) {
                if (randomNumber > 0.8) {
                    shoppingCart.addProduct(product);
                    sum += product.getPrice();
                    System.out.printf(number + " put " + product);
                } else {
                    boolean productInList = false;
                    while (!productInList) {
                        productInList = shoppingCart.removeProduct(product);
                    }
                    sum -= product.getPrice();
                }
            }

            int waitDuration = random.nextInt(999) + 1;
            Thread.currentThread().wait(waitDuration);
        }

        return sum;
    }
}
