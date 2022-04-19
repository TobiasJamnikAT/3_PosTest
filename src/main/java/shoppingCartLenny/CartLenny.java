package shoppingCartLenny;

import java.util.ArrayList;
import java.util.List;

public class CartLenny {
    private List<ProductLenny> products = new ArrayList<>();

    public CartLenny(List<ProductLenny> products) {
        this.products = products;
    }

    public void addProduct(ProductLenny product) {
        if (products.contains(product)) {
            ProductLenny productInList = products.get(products.indexOf(product));
            productInList.setQuantity(productInList.getQuantity() + 1);
            return;
        }
        product.setQuantity(1);
        products.add(product);
    }

    public boolean removeProduct(ProductLenny product) {
        ProductLenny productInList = products.get(products.indexOf(product));

        if (productInList == null) {
            return false;
        }

        if (productInList.getQuantity() > 1) {
            productInList.setQuantity(productInList.getQuantity() - 1);
        } else {
            products.remove(productInList);
        }
        return true;
    }
}
