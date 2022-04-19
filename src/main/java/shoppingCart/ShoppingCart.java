package shoppingCart;

import shoppingCart.beans.ConsoleColors;
import shoppingCart.beans.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ShoppingCart {
    private List<Product> productCart;

    public ShoppingCart(List<Product> productCart) {
        this.productCart = productCart;
    }

    public void addProduct(Product product, String name){
        if(productCart.contains(product)){
            int index = productCart.indexOf(product);
            productCart.get(index).setQuantity(productCart.get(index).getQuantity() + 1);
        } else{
            product.setQuantity(1);
            productCart.add(product);
        }
        System.out.println(name + " put: " +product.getName() + " - " + product.getPrice() + " €");
    }

    public boolean removeProduct(Product product, String name){
        if(productCart.contains(product) && productCart.get(productCart.indexOf(product)).getQuantity() > 1){
            int index = productCart.indexOf(product);
            productCart.get(index).setQuantity(productCart.get(index).getQuantity() - 1);
            System.out.println(name + " remove: " +product.getName() + " - " + product.getSum() + " €");
            return false;
        } else if (productCart.contains(product) && productCart.get(productCart.indexOf(product)).getQuantity() == 1){
            productCart.remove(product);
            System.out.println(name + " remove: " +product.getName() + " - " + product.getSum() + " €");
            return true;
        } else{
            Random random = new Random();
            try {
                productCart.wait(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ConsoleColors.RED + name + " is waiting for " +product.getName() + " - " + product.getPrice() + " €");
            return false;
        }
    }
}
