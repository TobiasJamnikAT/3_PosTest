package ShoppingCartWild;

import ShoppingCartWild.beans.WildesProduct;
import shoppingCart.ShoppingCart;

import java.util.Random;
import java.util.concurrent.Callable;

public class WildesCartWorker implements Callable<Double> {
    private static Random random = new Random();
    private int name;
    private WildesShoppingCart shoppingCart;

    public WildesCartWorker(int name, WildesShoppingCart shoppingCart) {
        this.name = name;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public Double call() throws Exception {
        System.out.printf("%s starts\n", name);
        double difference = 0.0;
        for (int i = 0; i < 10; i++) {
            WildesProduct item = WildesCartLauncher.productList.get(random.nextInt(WildesCartLauncher.productList.size()));

            synchronized (shoppingCart) {
                if (random.nextInt(10) < 2) {
                    if (!shoppingCart.getCart().contains(item)) {
                        System.err.printf("%d is waiting for %s - %s\n", name, item.getName(), item.getPrice());
                    }
                    for (int j = 0; j < 5 && !shoppingCart.getCart().contains(item); j++) {
                        shoppingCart.wait(100);
                    }
                    if (!shoppingCart.getCart().contains(item)) {
                        continue;
                    }
                    shoppingCart.removeProduct(item);
                    System.out.printf("%d remove: %s - %s\n", name, item.getName(), item.getPrice());
                    difference -= item.getPriceDouble();
                } else {
                    shoppingCart.addProduct(item);
                    shoppingCart.notifyAll();
                    System.out.printf("%d put: %s - %s\n", name, item.getName(), item.getPrice());
                    difference += item.getPriceDouble();
                }

            }
            Thread.sleep(random.nextInt(1000) + 1);
        }
        return difference;
    }
}
