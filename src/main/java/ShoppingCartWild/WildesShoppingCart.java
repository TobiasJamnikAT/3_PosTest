package ShoppingCartWild;

import ShoppingCartWild.beans.WildesProduct;

import java.util.ArrayList;
import java.util.List;

public class WildesShoppingCart {
    private List<WildesProduct> cart = new ArrayList<>();

    public void addProduct(WildesProduct product) {
        if (cart.contains(product)) {
            cart.get(cart.indexOf(product)).setQuantity(1 + cart.get(cart.indexOf(product)).getQuantity());
            return;
        }
        cart.add(product);
    }


    public void removeProduct(WildesProduct product) {
        if ((cart.get(cart.indexOf(product)).getQuantity()) > 1) {
            cart.get(cart.indexOf(product)).setQuantity(cart.get(cart.indexOf(product)).getQuantity() - 1);
            return;
        }
        cart.remove(product);
    }

    public List<WildesProduct> getCart() {
        return cart;
    }
}
