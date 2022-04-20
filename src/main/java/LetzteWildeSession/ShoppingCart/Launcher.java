package LetzteWildeSession.ShoppingCart;

import LetzteWildeSession.ShoppingCart_InvokeAll.Beans.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Product> productList = readProducts(Path.of("src/main/java/LetzteWildeSession/ShoppingCart/products_shoppingCard.csv"));
        LetzteWildeSession.ShoppingCart_InvokeAll.ShoppingCart shoppingCart = new LetzteWildeSession.ShoppingCart_InvokeAll.ShoppingCart(productList);

        ExecutorService pool = Executors.newFixedThreadPool(12);
        List<Callable<Double>> workerList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            workerList.add(new CartWorker(i, shoppingCart, productList));
        }

        List<Future<Double>> resultFutures = pool.invokeAll(workerList);
        double finalSum = 0.0;
        for (Future<Double> futureFalue : resultFutures){
            try {
                finalSum += futureFalue.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();

        System.out.println(String.format("Total sum of ShoppingCart: %.2f€",finalSum));
    }

    public static List<Product> readProducts(Path filePath) throws IOException {
        List<Product> products = Files.lines(filePath).skip(1).map(Product::new).collect(Collectors.toList());
        return products;
    }
}
