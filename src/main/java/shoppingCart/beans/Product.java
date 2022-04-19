package shoppingCart.beans;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String line) {
        String[] tokens = line.split(";€");
        this.name = tokens[0];
        this.price = Double.parseDouble(tokens[1]);
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

    public double getSum(){
        return price * quantity;
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

    @Override
    public String toString() {
        return String.format("%s costs %.2f - quantity = %d", name, price, quantity);
    }
}
