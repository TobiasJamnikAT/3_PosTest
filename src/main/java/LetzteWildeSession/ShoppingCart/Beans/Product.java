package LetzteWildeSession.ShoppingCart_InvokeAll.Beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String line) {
        //Dried Cranberries;€7,12
        String[] parts = line.split(";€");
        this.name = parts[0];
        this.price = Double.parseDouble(parts[1]);
        this.quantity = 1;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f €", name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
