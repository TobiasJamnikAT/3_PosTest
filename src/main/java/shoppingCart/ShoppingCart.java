package shoppingCart;

import shoppingCart.beans.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private List<Product> productCart;

    public ShoppingCart(List<Product> productCart) {
        this.productCart = productCart;
    }

    public void addProduct(Product product){
        if(productCart.contains(product)){
            int index = productCart.indexOf(product);
            productCart.get(index).setQuantity(productCart.get(index).getQuantity() + 1);
        } else{
            product.setQuantity(1);
            productCart.add(product);
        }
    }

    public boolean removeProduct(Product product){
        if(productCart.contains(product) && productCart.get(productCart.indexOf(product)).getQuantity() > 1){
            int index = productCart.indexOf(product);
            productCart.get(index).setQuantity(productCart.get(index).getQuantity() - 1);
            return false;
        } else if (productCart.contains(product) && productCart.get(productCart.indexOf(product)).getQuantity() == 1){
            productCart.remove(product);
            return true;
        } else{
            throw new RuntimeException("Product does not exist");
        }
    }
}
