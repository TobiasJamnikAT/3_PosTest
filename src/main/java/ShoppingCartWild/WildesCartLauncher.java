package ShoppingCartWild;

import ShoppingCartWild.beans.WildesProduct;
import shoppingCart.beans.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WildesCartLauncher {
    public static List<WildesProduct> productList;

    public static void main(String[] args) {


        try {
            loadProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Double>> workerList = new ArrayList<>();

        WildesShoppingCart shoppingCart = new WildesShoppingCart();

        for (int i = 0; i < 20; i++) {
            workerList.add(new WildesCartWorker(i, shoppingCart));
        }

        double result  = 0;

        try {
            List<Future<Double>> futures = pool.invokeAll(workerList);
            for(Future<Double> future : futures) {
                result =+ future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        pool.shutdown();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        System.out.printf("Total sum of ShoppingCart: %s €", numberFormat.format(result));
    }

    public static List<WildesProduct> loadProducts() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "ShoppingCartWild","products_shoppingCard.csv");
        List<WildesProduct> products = Files.lines(path).skip(1).map(WildesProduct::new).collect(Collectors.toList());
        return products;
    }
}
