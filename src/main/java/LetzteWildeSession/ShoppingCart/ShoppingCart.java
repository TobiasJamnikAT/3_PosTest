package LetzteWildeSession.ShoppingCart_InvokeAll;

import LetzteWildeSession.ShoppingCart_InvokeAll.Beans.Product;

import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if(products.contains(product)){
            int index = products.indexOf(product);
            products.get(index).setQuantity(products.get(index).getQuantity() + 1);
        } else{
            products.add(product);
        }
    }

    public boolean removeProducts(Product product){
        if(products.contains(product)){
           int index = products.indexOf(product);

            if(products.get(index).getQuantity() > 1){
                products.get(index).setQuantity(products.get(index).getQuantity() - 1);
            } else{
                products.remove(product);
            }

            return true;
        }

        return false;
    }
}
