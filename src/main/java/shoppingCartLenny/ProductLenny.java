package shoppingCartLenny;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductLenny {
    private String name;
    private double price;
    private int quantity;

    public ProductLenny(String line) {
        String[] tokens = line.split(";€");
        this.name = tokens[0];
        this.price = Double.parseDouble(tokens[1]);
    }

    @Override
    public String toString() {
        return String.format("%s - %,2f", name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductLenny that = (ProductLenny) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
