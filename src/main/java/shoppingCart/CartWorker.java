package shoppingCart;

import java.util.List;
import java.util.concurrent.Callable;

public class CartWorker implements Callable<Double> {
    private int noOperations;
    private List<String> products;
    private ShoppingCart cart;

    @Override
    public Double call() throws Exception {
        return null;
    }
}
