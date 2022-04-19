package shoppingCart;

import shoppingCart.beans.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ShoppingLauncher {
    public static void main(String[] args) throws IOException {
        ShoppingLauncher launcher = new ShoppingLauncher();
        Path path = Path.of("C:\\Users\\Tobias Jamnik\\IdeaProjects\\3_PosTest\\src\\main\\java\\shoppingCart\\products_shoppingCard.csv");
        List<Product> products = launcher.readProducts(path);

        ShoppingCart cart = new ShoppingCart(products);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletionService<Double> service = new ExecutorCompletionService<>(pool);

        for(int i = 0; i < 5; i++){
            service.submit(new CartWorker(i, cart, products));
        }
        pool.shutdown();

        double sum = 0;
        for(int i = 0; i < 5; i++){
            try {
                Future<Double> future = service.take();
                double res = future.get();
                sum = sum + res;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("Total som of shopping cart: %.2f €", sum));

    }

    public List<Product> readProducts(Path filepath) throws FileNotFoundException, IOException {
        return Files.lines(filepath)
                .skip(1)
                .map(Product::new)
                .collect(Collectors.toList());
    }
}
