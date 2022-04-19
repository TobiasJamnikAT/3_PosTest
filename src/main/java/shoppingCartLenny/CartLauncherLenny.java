package shoppingCartLenny;

import shoppingCart.ShoppingCart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CartLauncherLenny {


    public static void main(String[] args) throws IOException {
        /*Path path = Path.of("C:\\Users\\Tobias Jamnik\\IdeaProjects\\3_PosTest\\src\\main\\java\\shoppingCartLenny\\products_shoppingCard.csv");
        List<ProductLenny> products = new ArrayList<>();
        CartLenny shoppingCart;

        products = Files.lines(path)
                .skip(1)
                .map(ProductLenny::new)
                .collect(Collectors.toList());
        shoppingCart = new CartLenny(products);

        Random randy = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletionService<Double> service = new ExecutorCompletionService<>(pool);

        for (int i = 1; i <=5; i++){
            service.submit(new CartWorkerLenny(i, products.get(randy.nextInt(products.size() - 1)), shoppingCart));
        }
        pool.shutdown();*/
        Path path = Path.of("C:\\Users\\Tobias Jamnik\\IdeaProjects\\3_PosTest\\src\\main\\java\\shoppingCartLenny\\products_shoppingCard.csv");
        List<ProductLenny> products = new ArrayList<>();
        CartLenny shoppingCart;

        products = Files.lines(path)
                .skip(1)
                .map(ProductLenny::new)
                .collect(Collectors.toList());
        shoppingCart = new CartLenny(products);

        Random randy = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Callable<Double>> workerList = new ArrayList<>();

        for (int i = 1; i <=5; i++){
            workerList.add(new CartWorkerLenny(i, products.get(randy.nextInt(products.size() - 1)), shoppingCart));
        }

        try {
            List<Future<Double>> resies = pool.invokeAll(workerList);
            List<Double> resiesMesies = new ArrayList<>();

            for(Future<Double> r : resies){
                resiesMesies.add(r.get());
            }

            double summieMummie = 0;
            for(Double res : resiesMesies){
                summieMummie += res;
            }
            System.out.println("Total sum of shopping cart: " +summieMummie);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
