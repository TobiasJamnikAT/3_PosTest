package LetzteWildeSession.ShoppingCart;

import LetzteWildeSession.ShoppingCart_InvokeAll.Beans.Product;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CartWorker implements Callable<Double> {
    private int name;
    private int noOperations;
    private LetzteWildeSession.ShoppingCart_InvokeAll.ShoppingCart shoppingCart;
    private List<Product> productList;

    public CartWorker(int name, LetzteWildeSession.ShoppingCart_InvokeAll.ShoppingCart shoppingCart, List<Product> productList) {
        this.name = name;
        this.shoppingCart = shoppingCart;
        this.productList = productList;
        this.noOperations = 10;
    }

    @Override
    public Double call() throws Exception {
        Random random = new Random();
        double value = 0;
        System.out.println(name + "starts");

        for(int i = 0; i < noOperations; i++){
            int oprtation = random.nextInt(100);
            Product product = productList.get(random.nextInt(productList.size()));

            synchronized (shoppingCart){
                if (oprtation > 19 && oprtation < 100) {
                    System.out.println(name + " put:" +product.toString());
                    shoppingCart.addProduct(product);
                    value += product.getPrice() * product.getQuantity();
                    shoppingCart.notifyAll();
                } else{
                    if(productList.contains(product)){
                        shoppingCart.removeProducts(product);
                        value -= product.getPrice() * product.getQuantity();
                        System.out.println(name + " remove:" +product.toString());
                    } else{
                        System.err.println(name + " is waiting for " +product.toString());
                        shoppingCart.wait(random.nextInt(1000));
                    }
                }
            }

            Thread.sleep(random.nextInt(1000));
        }

        return value;
    }
}
